package com.evol.movies.presentation.mapper

import com.evol.movies.domain.movie.Movie
import com.evol.movies.presentation.model.MovieUiModel

fun Movie.toUIModel() = MovieUiModel(
    id = id,
    posterPath = posterPath,
    title = title,
    voteAverage = voteAverage,
    overview = overview,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    isAdultContent = isAdultContent
)