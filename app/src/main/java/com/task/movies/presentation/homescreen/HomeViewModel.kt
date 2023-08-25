package com.task.movies.presentation.homescreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.task.movies.domain.models.*
import com.task.movies.domain.use_cases.UseCases
import com.task.movies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _selectedGenre = mutableStateOf("")
    val selectedGenre: State<String> = _selectedGenre

    fun setGenre(genres: String){
        _selectedGenre.value = genres
    }

    private val _getMovieGenres = mutableStateOf<List<Genres>>(emptyList())
    val movieGenres: State<List<Genres>> = _getMovieGenres

    private val _discoverMovies = mutableStateOf<Flow<PagingData<DiscoverMovies>>>(emptyFlow())
    val discoverMovies: State<Flow<PagingData<DiscoverMovies>>> = _discoverMovies


    init {
        discoverMovies(null)
        getMovieGenres()
    }


    fun discoverMovies(genreId: Int?) {
        viewModelScope.launch {
            _discoverMovies.value = if (genreId != null) {
                useCases.getDiscoverMoviesUseCase().map { pagingData ->
                    pagingData.filter {
                        it.genre_ids.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                useCases.getDiscoverMoviesUseCase().cachedIn(viewModelScope)
            }
        }
    }

    private fun getMovieGenres(){
        viewModelScope.launch {
            when(val result =useCases.getMovieGenresUseCase()){
                is Resource.Success -> {
                    _getMovieGenres.value = result.data?.genres!!

                }is Resource.Error ->{

                }else ->{}
            }
        }
    }
}
