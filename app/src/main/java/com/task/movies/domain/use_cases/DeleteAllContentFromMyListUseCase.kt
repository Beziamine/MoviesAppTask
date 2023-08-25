package com.task.movies.domain.use_cases

import com.task.movies.data.repository.Repository

class DeleteAllContentFromMyListUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(){
        return repository.deleteAllContentFromMyList()
    }
}