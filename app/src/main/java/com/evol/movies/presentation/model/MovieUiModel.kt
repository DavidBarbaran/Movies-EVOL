package com.evol.movies.presentation.model

data class MovieUiModel (
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    val backdropPath: String,
    val releaseDate: String,
)