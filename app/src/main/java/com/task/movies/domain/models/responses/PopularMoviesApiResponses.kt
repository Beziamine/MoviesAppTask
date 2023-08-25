package com.task.movies.domain.models.responses

import com.google.gson.annotations.SerializedName
import com.task.movies.domain.models.PopularMovies

data class PopularMoviesApiResponses(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searches: List<PopularMovies>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
