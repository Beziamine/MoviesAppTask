package com.task.movies.domain.repository

import androidx.paging.PagingData
import com.task.movies.domain.models.*
import com.task.movies.domain.models.responses.*
import com.task.movies.util.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getMovieDetails(filmId: Int): Resource<MoviesDetails>
    suspend fun getMovieGenres(): Resource<GenresApiResponses>
    fun getDiscoverMovies(): Flow<PagingData<DiscoverMovies>>
}