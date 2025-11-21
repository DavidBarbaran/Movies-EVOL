package com.evol.movies.data.mapper

import com.evol.movies.data.source.remote.entity.MovieResponseEntity
import com.evol.movies.data.util.BACKDROP_BASE_URL
import com.evol.movies.data.util.POSTER_BASE_URL
import com.evol.movies.domain.movie.Movie

fun MovieResponseEntity.toDomain() = Movie(
    id = id,
    posterPath = POSTER_BASE_URL + posterPath,
    title = title,
    voteAverage = voteAverage,
    overview = overview,
    backdropPath = BACKDROP_BASE_URL + backdropPath,
    releaseDate = releaseDate,
    isAdultContent = isAdultContent
)