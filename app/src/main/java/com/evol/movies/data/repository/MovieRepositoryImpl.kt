package com.evol.movies.data.repository

import com.evol.movies.data.exception.NetworkUnavailableException
import com.evol.movies.data.source.local.MoviesLocalDataSource
import com.evol.movies.data.source.remote.MoviesRemoteDataSource
import com.evol.movies.data.source.remote.NetworkChecker
import com.evol.movies.domain.movie.Movie
import com.evol.movies.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: MoviesRemoteDataSource,
    private val local: MoviesLocalDataSource,
    private val networkChecker: NetworkChecker
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> = withContext(Dispatchers.IO) {
        val localMovies = local.getMoviesPage(page)

        if (localMovies.isNotEmpty()) {
            return@withContext localMovies
        }

        if (networkChecker.isNetworkAvailable().not()) {
            throw NetworkUnavailableException()
        }

        val remoteMovies = remote.getPopularMovies(page)

        local.saveMovies(remoteMovies)
        return@withContext local.getMoviesPage(page)
    }

    override suspend fun getMovieById(id: Int): Movie? = withContext(Dispatchers.IO) {
        return@withContext local.getMovieById(id)
    }
}