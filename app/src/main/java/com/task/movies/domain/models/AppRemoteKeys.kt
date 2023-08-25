package com.task.movies.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.task.movies.util.Constants.APP_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = APP_REMOTE_KEYS_DATABASE_TABLE)
data class AppRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)