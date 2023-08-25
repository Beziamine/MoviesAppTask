package com.task.movies.domain.use_cases

import com.task.movies.data.repository.Repository

class IfExistsUseCase(
    private val repository: Repository
) {
     suspend operator fun invoke(listId:Int):Int {
        return repository.ifExists(listId)
    }
}