package com.task.movies.domain.use_cases

import com.task.movies.data.repository.Repository
import com.task.movies.domain.models.MyList

class AddToMyListUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(myList: MyList){
        return repository.addToMyList(myList =myList)
    }
}