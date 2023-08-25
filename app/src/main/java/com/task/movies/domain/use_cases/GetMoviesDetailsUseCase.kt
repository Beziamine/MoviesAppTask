package com.task.movies.domain.use_cases

import com.task.movies.data.repository.Repository
import com.task.movies.domain.models.MoviesDetails
import com.task.movies.util.Resource

class GetMoviesDetailsUseCase(
    val repository: Repository
) {
    suspend operator fun invoke(filmId: Int): Resource<MoviesDetails>{
        return repository.getMoviesDetails(filmId)
    }
}