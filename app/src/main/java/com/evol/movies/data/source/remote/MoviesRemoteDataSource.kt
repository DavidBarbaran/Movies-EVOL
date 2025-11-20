package com.evol.movies.data.source.remote

import com.evol.movies.data.mapper.toDomain
import com.evol.movies.data.api.MoviesApi
import com.evol.movies.domain.movie.Movie
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val api: MoviesApi,
) {

    suspend fun getPopularMovies(page: Int): List<Movie> {
        return api.getMovies(page).results.map { it.toDomain() }
    }
}