package com.task.movies.di

import android.content.Context
import com.task.movies.data.repository.DataStoreOperationsImp
import com.task.movies.data.repository.Repository
import com.task.movies.domain.repository.DataStoreOperations
import com.task.movies.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context): DataStoreOperations{
        return DataStoreOperationsImp(context = context)
    }

    @Provides
    @Singleton
    fun providesUseCases(repository: Repository): UseCases{
        return UseCases(
            addToMyListUseCase = AddToMyListUseCase(repository),
            deleteAllContentFromMyListUseCase = DeleteAllContentFromMyListUseCase(repository),
            deleteOneFromMyListUseCase = DeleteOneFromMyListUseCase(repository),
            getMovieGenresUseCase = GetMovieGenresUseCase(repository),
            getMyListUseCase = GetMyListUseCase(repository),
            getDiscoverMoviesUseCase = GetDiscoverMoviesUseCase(repository),
            getMoviesDetailsUseCase = GetMoviesDetailsUseCase(repository),
            ifExistsUseCase = IfExistsUseCase(repository)
        )
    }
}