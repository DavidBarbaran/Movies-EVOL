package com.evol.movies.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.evol.movies.presentation.feature.detail.MovieDetailScreen
import com.evol.movies.presentation.feature.movies.MoviesListScreen
import com.evol.movies.presentation.navigation.Screen
import com.evol.movies.presentation.theme.MoviesEVOLTheme
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
    NavHost(rootNavController, startDestination = Screen.MoviesList.route) {
        composable(Screen.MoviesList.route) {
            MoviesListScreen(rootNavController)
        }

        composable(
            route = Screen.MoviesDetail.route,
            arguments = listOf(
                navArgument(Screen.MoviesDetail.PARAM_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            val movieId = it.arguments?.getInt(Screen.MoviesDetail.PARAM_ID)
            MovieDetailScreen(rootNavController, movieId)
        }
    }
}