package com.task.movies.domain.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguages(
    @SerializedName("english_name")
    val englishName: String?,
    @SerializedName("name")
    val name: String
)