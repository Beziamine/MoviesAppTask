package com.task.movies.presentation.details

import androidx.lifecycle.ViewModel
import com.task.movies.domain.models.*
import com.task.movies.domain.use_cases.UseCases
import com.task.movies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
): ViewModel() {

    suspend fun getMoviesDetails(filmId: Int): Resource<MoviesDetails> {
        return useCases.getMoviesDetailsUseCase(filmId)
    }
}