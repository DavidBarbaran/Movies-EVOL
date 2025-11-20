package com.evol.movies.domain.repository

import com.evol.movies.domain.movie.Movie

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): List<Movie>
    suspend fun getMovieById(id: Int): Movie?
}