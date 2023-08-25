package com.task.movies.domain.use_cases

import com.task.movies.data.repository.Repository
import com.task.movies.domain.models.MyList
import kotlinx.coroutines.flow.Flow

class GetMyListUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<MyList>> {
        return repository.getMyList()
    }
}