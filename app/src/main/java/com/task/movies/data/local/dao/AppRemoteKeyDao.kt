package com.task.movies.data.local.dao

import androidx.room.*
import com.task.movies.domain.models.AppRemoteKeys

@Dao
interface AppRemoteKeyDao {

    @Query("SELECT * FROM APP_REMOTE_KEYS_TABLE WHERE id=:remoteKey")
    suspend fun getAppRemoteKeys(remoteKey: Int): AppRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllAppRemoteKeys(appRemoteKeys: List<AppRemoteKeys>)

    @Query("DELETE FROM APP_REMOTE_KEYS_TABLE")
    suspend fun deleteAllAppRemoteKeys()
}