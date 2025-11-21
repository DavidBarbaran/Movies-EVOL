package com.evol.movies.data.source.local

import com.evol.movies.data.source.local.entity.MovieDatabaseEntity
import com.evol.movies.data.mapper.toDatabaseEntity
import com.evol.movies.data.mapper.toDomain
import com.evol.movies.domain.movie.Movie
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val realm: Realm,
) {

    suspend fun saveMovies(movies: List<Movie>) {
        realm.write {
            movies.forEach { movie ->
                copyToRealm(movie.toDatabaseEntity(), updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    fun getMoviesPage(page: Int, pageSize: Int = 20): List<Movie> {
        val skip = (page - 1) * pageSize

        return realm.query<MovieDatabaseEntity>()
            .find()
            .drop(skip)
            .take(pageSize)
            .map { it.toDomain() }
    }

    fun getMovieById(id: Int): Movie? {
        return realm.query<MovieDatabaseEntity>("id == $0", id)
            .first()
            .find()
            ?.toDomain()
    }
}