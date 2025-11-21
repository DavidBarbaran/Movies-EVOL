package com.evol.movies.domain.detail

import com.evol.movies.domain.movie.Movie
import com.evol.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int): Movie? {
        return repository.getMovieById(id)
    }
}