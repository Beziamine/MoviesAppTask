package com.task.movies.domain.use_cases

import com.task.movies.data.repository.Repository
import com.task.movies.domain.models.responses.GenresApiResponses
import com.task.movies.util.Resource

class GetMovieGenresUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): Resource<GenresApiResponses>{
        return repository.getMovieGenres()
    }
}