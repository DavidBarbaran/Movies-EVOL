package com.evol.movies.presentation.feature.movies

import com.evol.movies.presentation.model.MovieUiModel

data class MoviesUiState(
    val movies: List<MovieUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val page: Int = 1
)