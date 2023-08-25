package com.task.movies.di

import android.content.Context
import androidx.room.Room
import com.task.movies.data.local.AppDatabase
import com.task.movies.data.repository.LocalDataSourceImp
import com.task.movies.domain.repository.LocalDataSource
import com.task.movies.util.Constants.APP_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            APP_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(appDatabase: AppDatabase): LocalDataSource{
        return LocalDataSourceImp(appDatabase = appDatabase )
    }
}