package com.evol.movies.presentation.feature.movies

sealed class MoviesEvent {
    data class ShowError(val message: String) : MoviesEvent()
}