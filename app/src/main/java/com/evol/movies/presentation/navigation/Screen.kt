package com.evol.movies.presentation.navigation

sealed class Screen(val route: String) {
    data object MoviesList : Screen("home_screen")
    data object MoviesDetail : Screen("detail_screen?id={id}") {
        const val PARAM_ID = "id"
        fun createRoute(id: Int) = "detail_screen?$PARAM_ID=$id"
    }
}