package com.evol.movies.domain.movie

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): List<Movie>
}