package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import android.content.Context

@Entity(tableName = "favorites")
data class FavoriteStation(
    @PrimaryKey val id: String,
    val name: String,
    val streamUrl: String,
    val logoUrl: String,
    val category: String
)

data class RadioStation(
    val id: String,
    val name: String,
    val streamUrl: String,
    val logoUrl: String,
    val category: String,
    val isFavorite: Boolean = false,
    val isUserAdded: Boolean = false
)

object StationData {
    val stations = listOf(
        RadioStation("1", "City FM", "https://stream-289.zeno.fm/53g2h8033d0uv?zt=eyJhbGciOiJIUzI1NiJ9.eyJzdHJlYW0iOiI1M2cyaDgwMzNkMHV2IiwiaG9zdCI6InN0cmVhbS0yODkuemVuby5mbSIsInJ0dGwiOjUsImp0aSI6Ii01eU81REd1UmNDQms2TnhJSUhYR0EiLCJpYXQiOjE3ODAyMzYxMTIsImV4cCI6MTc4MDIzNjE3Mn0.rfGvihadJ-nIMFVu-p8AyjG_qo3q8O8olGW_DevoYTY", "https://static2.mytuner.mobi/media/tvos_radios/sh2vnjbnwjhl.png", "Sinhala"),
        RadioStation("2", "Swadesheeya Sewaya", "https://stream-288.zeno.fm/yrmzpgy33d0uv?zt=eyJhbGciOiJIUzI1NiJ9.eyJzdHJlYW0iOiJ5cm16cGd5MzNkMHV2IiwiaG9zdCI6InN0cmVhbS0yODguemVuby5mbSIsInJ0dGwiOjUsImp0aSI6IkNCcndKRzctUmJpc0RsUEdtTndqY3ciLCJpYXQiOjE3ODAyMzYyMjAsImV4cCI6MTc4MDIzNjI4MH0.Slj9MPNJ-QPcN3UwE8vGrHE34rP_OgPIwrVUoMpOTUA", "https://static2.mytuner.mobi/media/tvos_radios/sh2vnjbnwjhl.png", "Sinhala"),
        RadioStation("3", "Kandurata Sewaya", "https://stream-289.zeno.fm/ncrf3ma43d0uv?zt=eyJhbGciOiJIUzI1NiJ9.eyJzdHJlYW0iOiJuY3JmM21hNDNkMHV2IiwiaG9zdCI6InN0cmVhbS0yODkuemVuby5mbSIsInJ0dGwiOjUsImp0aSI6ImRxMVNEN2dfUlYtRFd2WVhPd1ZOWHciLCJpYXQiOjE3ODAyMzYyODIsImV4cCI6MTc4MDIzNjM0Mn0.c9t-Cblt_TNX-KGh1O9iYv6UccCvmqFAn7f9SAfdpog", "https://static2.mytuner.mobi/media/tvos_radios/sh2vnjbnwjhl.png", "Sinhala"),
        RadioStation("4", "Rajarata Sewaya", "http://220.247.227.20:8000/rajaratastream", "https://static2.mytuner.mobi/media/tvos_radios/sh2vnjbnwjhl.png", "Sinhala"),
        RadioStation("5", "Neth FM", "https://cp11.serverse.com/proxy/nethfm/stream", "https://static2.mytuner.mobi/media/tvos_radios/031/neth-fm.560b608f.jpg", "Sinhala"),
        RadioStation("6", "Sirasa FM", "https://mbc.thestreamtech.com:8087/index.html", "https://static2.mytuner.mobi/media/tvos_radios/uf6uwduxygbg.png", "Sinhala"),
        RadioStation("7", "Ran FM", "https://a3.asurahosting.com/listen/ranfm/radio.mp3", "https://static2.mytuner.mobi/media/tvos_radios/6kygbcanmevr.png", "Sinhala"),
        RadioStation("8", "Hiru FM", "https://radio.lotustechnologieslk.net:2020/stream/hirufmgarden", "https://static2.mytuner.mobi/media/tvos_radios/9rnk3m49cP.png", "Sinhala"),
        RadioStation("9", "Siyatha FM", "https://dc02.onlineradio.voaplus.com/siyathafm", "https://static2.mytuner.mobi/media/tvos_radios/py7q4qabeckr.png", "Sinhala"),
        RadioStation("10", "Lakhanda FM", "https://cp12.serverse.com/proxy/itnfm?mp=/stream", "https://static2.mytuner.mobi/media/tvos_radios/tbmsquzqeuyf.png", "Sinhala"),
        RadioStation("11", "Shree FM", "https://a3.asurahosting.com/listen/shreefm/radio.mp3", "https://static2.mytuner.mobi/media/tvos_radios/p8bulmuanser.png", "Sinhala"),
        RadioStation("12", "Sha FM", "https://radio.lotustechnologieslk.net:2020/stream/shaafmgarden", "https://static2.mytuner.mobi/media/tvos_radios/3wn9jyvhpgua.png", "Sinhala"),
        RadioStation("13", "Yes FM", "https://mbc.thestreamtech.com:7056/index.html/stream", "https://static2.mytuner.mobi/media/tvos_radios/v7zgxsvh3skn.png", "English"),
        RadioStation("14", "Shakthi FM", "https://mbc.thestreamtech.com:8086/index.html", "https://static2.mytuner.mobi/media/tvos_radios/n8F2aF93Yh.png", "Tamil"),
        RadioStation("15", "FM Derana", "https://cp12.serverse.com/proxy/fmderana/stream", "https://static2.mytuner.mobi/media/tvos_radios/jtyz9u8efrur.jpg", "Sinhala")
    )
}

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteStation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(station: FavoriteStation)

    @Delete
    suspend fun deleteFavorite(station: FavoriteStation)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteFavoriteById(id: String)
}

@Entity(tableName = "user_stations")
data class UserStation(
    @PrimaryKey val id: String,
    val name: String,
    val streamUrl: String,
    val logoUrl: String,
    val category: String
)

@Dao
interface UserStationDao {
    @Query("SELECT * FROM user_stations")
    fun getAllUserStations(): Flow<List<UserStation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserStation(station: UserStation)

    @Query("DELETE FROM user_stations WHERE id = :id")
    suspend fun deleteUserStationById(id: String)
}

@Database(entities = [FavoriteStation::class, UserStation::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun userStationDao(): UserStationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "radio_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class RadioRepository(private val favoriteDao: FavoriteDao, private val userStationDao: UserStationDao) {
    val favorites: Flow<List<FavoriteStation>> = favoriteDao.getAllFavorites()
    val userStations: Flow<List<UserStation>> = userStationDao.getAllUserStations()

    suspend fun toggleFavorite(station: RadioStation) {
        if (station.isFavorite) {
            favoriteDao.deleteFavoriteById(station.id)
        } else {
            val fav = FavoriteStation(station.id, station.name, station.streamUrl, station.logoUrl, station.category)
            favoriteDao.insertFavorite(fav)
        }
    }
    
    suspend fun addUserStation(station: UserStation) {
        userStationDao.insertUserStation(station)
    }
    
    suspend fun deleteUserStation(id: String) {
        userStationDao.deleteUserStationById(id)
        favoriteDao.deleteFavoriteById(id)
    }
}
