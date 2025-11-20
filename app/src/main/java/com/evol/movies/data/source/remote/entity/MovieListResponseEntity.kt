package com.evol.movies.data.source.remote.entity

import com.google.gson.annotations.SerializedName

data class MovieListResponseEntity(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<MovieResponseEntity>
)