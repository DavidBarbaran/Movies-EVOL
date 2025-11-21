package com.evol.movies.presentation.feature.detail

import com.evol.movies.presentation.model.MovieUiModel

data class MovieDetailUiState (
    val movie: MovieUiModel = MovieUiModel(),
    val isLoading: Boolean = false,
)