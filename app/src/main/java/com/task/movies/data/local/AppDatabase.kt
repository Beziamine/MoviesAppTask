package com.task.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.movies.data.local.dao.AppRemoteKeyDao
import com.task.movies.data.local.dao.MyListDao
import com.task.movies.domain.models.AppRemoteKeys
import com.task.movies.domain.models.MyList


@Database(
    entities = [MyList::class, AppRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun AppRemoteKeyDao(): AppRemoteKeyDao
    abstract fun myListDao(): MyListDao
}