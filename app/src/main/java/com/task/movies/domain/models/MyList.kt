package com.task.movies.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.task.movies.util.Constants.TABLE_NAME

@Entity(tableName =TABLE_NAME)
data class MyList(
    @PrimaryKey
    val listId: Int,
    val imagePath: String?,
    val title: String,
    val description: String,
    val mediaType: String,
    val releaseDate : String
)

