package com.evol.movies.data.source.local

import com.evol.movies.data.source.local.entity.MovieDatabaseEntity
import com.evol.movies.data.mapper.toDatabaseEntity
import com.evol.movies.data.mapper.toDomain
import com.evol.movies.domain.movie.Movie
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val realm: Realm
) {

    suspend fun saveMovies(movies: List<Movie>) {
        realm.write {
            movies.forEach { movie ->
                copyToRealm(movie.toDatabaseEntity(), updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    suspend fun getMoviesPage(page: Int, pageSize: Int = 20): List<Movie> = withContext(Dispatchers.IO) {
            val skip = (page - 1) * pageSize

            realm.query<MovieDatabaseEntity>()
                .find()
                .drop(skip)
                .take(pageSize)
                .map { it.toDomain() }
        }
}