package com.task.movies.domain.use_cases

import com.task.movies.data.repository.Repository

class DeleteOneFromMyListUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(myList: Int){
        return repository.deleteOneFromMyList(myList = myList)
    }
}