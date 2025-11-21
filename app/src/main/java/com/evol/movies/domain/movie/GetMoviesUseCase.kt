package com.evol.movies.domain.movie

import com.evol.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(page: Int): List<Movie> = repository.getPopularMovies(page)
}