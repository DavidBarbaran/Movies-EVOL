package com.evol.movies.presentation.model

data class MovieUiModel (
    val id: Int = 0,
    val posterPath: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
    val overview: String = "",
    val backdropPath: String = "",
    val releaseDate: String = "",
    val isAdultContent: Boolean = false
)