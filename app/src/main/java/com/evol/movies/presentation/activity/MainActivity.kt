package com.evol.movies.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.evol.movies.presentation.feature.movies.MoviesListScreen
import com.evol.movies.presentation.navigation.Screen
import com.evol.movies.presentation.ui.theme.MoviesEVOLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesEVOLTheme {
                MainNavHost()
            }
        }
    }
}

@Composable
fun MainNavHost() {
    val rootNavController = rememberNavController()
    NavHost(rootNavController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            MoviesListScreen()
        }
    }
}