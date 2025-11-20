package com.evol.movies.data.api

import com.evol.movies.data.source.remote.entity.MovieListResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getMovies(@Query("page") page: Int): MovieListResponseEntity
}