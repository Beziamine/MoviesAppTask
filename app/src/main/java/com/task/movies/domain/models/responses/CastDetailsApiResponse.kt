package com.task.movies.domain.models.responses

import com.task.movies.domain.models.CastDetails
import com.google.gson.annotations.SerializedName

data class CastDetailsApiResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val casts: List<CastDetails>
)
