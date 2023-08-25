package com.task.movies.domain.use_cases

import androidx.paging.PagingData
import com.task.movies.data.repository.Repository
import com.task.movies.domain.models.DiscoverMovies
import kotlinx.coroutines.flow.Flow

class GetDiscoverMoviesUseCase(
    private val repository: Repository)
{
    operator fun invoke(): Flow<PagingData<DiscoverMovies>> {
        return repository.getDiscoverMovies()
    }
}
