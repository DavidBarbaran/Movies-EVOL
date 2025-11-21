package com.evol.movies.domain.movie

data class Movie(
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    val backdropPath: String,
    val releaseDate: String,
    val isAdultContent: Boolean
)