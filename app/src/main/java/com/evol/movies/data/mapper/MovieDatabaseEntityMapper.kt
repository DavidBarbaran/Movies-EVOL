package com.evol.movies.data.mapper

import com.evol.movies.data.source.local.entity.MovieDatabaseEntity
import com.evol.movies.data.util.BACKDROP_BASE_URL
import com.evol.movies.data.util.POSTER_BASE_URL
import com.evol.movies.domain.movie.Movie
import kotlin.apply

fun MovieDatabaseEntity.toDomain() = Movie(
    id = id,
    posterPath = POSTER_BASE_URL + posterPath,
    title = title,
    voteAverage = voteAverage,
    overview = overview,
    backdropPath = BACKDROP_BASE_URL + backdropPath,
    releaseDate = releaseDate,
    isAdultContent = isAdultContent,
)

fun Movie.toDatabaseEntity() = MovieDatabaseEntity().apply {
    id = this@toDatabaseEntity.id
    posterPath = POSTER_BASE_URL + this@toDatabaseEntity.posterPath
    title = this@toDatabaseEntity.title
    voteAverage = this@toDatabaseEntity.voteAverage
    overview = this@toDatabaseEntity.overview
    backdropPath = BACKDROP_BASE_URL + this@toDatabaseEntity.backdropPath
    releaseDate = this@toDatabaseEntity.releaseDate
    isAdultContent = this@toDatabaseEntity.isAdultContent
}