package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.AppDatabase
import com.example.data.RadioRepository
import com.example.ui.MainScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.RadioViewModel
import com.example.viewmodel.RadioViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: RadioViewModel by viewModels {
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = RadioRepository(database.favoriteDao(), database.userStationDao())
        RadioViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        viewModel.initializeController(this)
        
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        MainScreen(
                            viewModel = viewModel,
                            onNavigateToAddStation = { navController.navigate("add_station") }
                        )
                    }
                    composable("add_station") {
                        com.example.ui.AddStationScreen(
                            viewModel = viewModel,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
