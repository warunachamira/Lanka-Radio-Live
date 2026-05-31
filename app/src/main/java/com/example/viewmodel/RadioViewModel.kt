package com.example.viewmodel

import android.content.ComponentName
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.data.RadioRepository
import com.example.data.RadioStation
import com.example.data.StationData
import com.example.service.RadioService
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RadioViewModel(private val repository: RadioRepository) : ViewModel() {
    private var controllerFuture: ListenableFuture<MediaController>? = null
    private var mediaController: MediaController? = null

    private val _currentStation = MutableStateFlow<RadioStation?>(null)
    val currentStation = _currentStation.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    
    private val _sortAscending = MutableStateFlow(true)
    val sortAscending = _sortAscending.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
    
    fun toggleSortOrder() {
        _sortAscending.value = !_sortAscending.value
    }

    val stations: StateFlow<List<RadioStation>> = combine(
        repository.favorites,
        repository.userStations,
        _searchQuery,
        _sortAscending
    ) { favorites, userList, query, sortAsc ->
        val combined = StationData.stations + userList.map { 
            RadioStation(it.id, it.name, it.streamUrl, it.logoUrl, it.category, isUserAdded = true) 
        }
        var filteredAndSorted = combined.map { station ->
            station.copy(isFavorite = favorites.any { it.id == station.id })
        }
        
        if (query.isNotBlank()) {
            filteredAndSorted = filteredAndSorted.filter { it.name.contains(query, ignoreCase = true) }
        }
        
        if (sortAsc) {
            filteredAndSorted = filteredAndSorted.sortedBy { it.name }
        } else {
            filteredAndSorted = filteredAndSorted.sortedByDescending { it.name }
        }
        filteredAndSorted
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun initializeController(context: Context) {
        if (controllerFuture != null) return
        val sessionToken = SessionToken(context, ComponentName(context, RadioService::class.java))
        controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture?.addListener({
            mediaController = controllerFuture?.get()
            setupController()
        }, MoreExecutors.directExecutor())
    }

    private fun setupController() {
        val controller = mediaController ?: return
        controller.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.value = isPlaying
            }
            override fun onPlaybackStateChanged(playbackState: Int) {
                _isLoading.value = playbackState == Player.STATE_BUFFERING
            }
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                val mediaId = mediaItem?.mediaId
                val found = stations.value.find { it.id == mediaId }
                if (found != null && _currentStation.value?.id != found.id) {
                    _currentStation.value = found
                }
            }
        })
        
        if (controller.currentMediaItem != null) {
            val mediaId = controller.currentMediaItem?.mediaId
            _currentStation.value = stations.value.find { it.id == mediaId }
            _isPlaying.value = controller.isPlaying
        }
    }

    fun playStation(station: RadioStation) {
        val controller = mediaController ?: return
        if (controller.currentMediaItem?.mediaId == station.id) {
            if (!controller.isPlaying) controller.play()
            return
        }
        
        val mediaMetadata = MediaMetadata.Builder()
            .setTitle(station.name)
            .setArtist("Lanka Radio Live")
            .setArtworkUri(android.net.Uri.parse(station.logoUrl))
            .build()
            
        val mediaItem = MediaItem.Builder()
            .setMediaId(station.id)
            .setUri(station.streamUrl)
            .setMediaMetadata(mediaMetadata)
            .build()
            
        controller.setMediaItem(mediaItem)
        controller.prepare()
        controller.play()
        _currentStation.value = station
    }

    fun togglePlayPause() {
        val controller = mediaController ?: return
        if (controller.isPlaying) {
            controller.pause()
        } else {
            controller.play()
        }
    }
    
    fun toggleFavorite(station: RadioStation) {
        viewModelScope.launch {
            repository.toggleFavorite(station)
        }
    }
    
    fun addStation(name: String, streamUrl: String, logoUrl: String, category: String) {
        viewModelScope.launch {
            val id = java.util.UUID.randomUUID().toString()
            val finalLogoUrl = if (logoUrl.isBlank()) "https://static2.mytuner.mobi/media/tvos_radios/sh2vnjbnwjhl.png" else logoUrl
            repository.addUserStation(com.example.data.UserStation(id, name, streamUrl, finalLogoUrl, category))
        }
    }
    
    fun deleteUserStation(station: RadioStation) {
        viewModelScope.launch {
            repository.deleteUserStation(station.id)
            if (_currentStation.value?.id == station.id) {
                mediaController?.stop()
                _currentStation.value = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        controllerFuture?.let { MediaController.releaseFuture(it) }
    }
}

class RadioViewModelFactory(private val repository: RadioRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RadioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RadioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
