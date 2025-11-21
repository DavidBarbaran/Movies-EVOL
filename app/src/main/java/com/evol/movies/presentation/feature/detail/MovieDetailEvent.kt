package com.evol.movies.presentation.feature.detail

sealed class MovieDetailEvent {
    object ShowError : MovieDetailEvent()
}