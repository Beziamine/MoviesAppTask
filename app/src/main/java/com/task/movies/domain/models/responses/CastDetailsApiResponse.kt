package com.task.movies.domain.models.responses

import com.google.gson.annotations.SerializedName
import com.task.movies.domain.models.CastDetails

data class CastDetailsApiResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val casts: List<CastDetails>
)
