package com.task.movies.domain.models.responses

import com.task.movies.domain.models.Genres
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GenresApiResponses(
    @SerializedName("genres")
    val genres: List<Genres>
)
