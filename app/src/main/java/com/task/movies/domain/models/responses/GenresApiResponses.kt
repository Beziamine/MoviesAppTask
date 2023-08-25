package com.task.movies.domain.models.responses

import com.google.gson.annotations.SerializedName
import com.task.movies.domain.models.Genres
import kotlinx.serialization.Serializable

@Serializable
data class GenresApiResponses(
    @SerializedName("genres")
    val genres: List<Genres>
)
