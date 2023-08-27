package com.task.movies.presentation.homescreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.task.movies.domain.models.DiscoverMovies
import com.task.movies.domain.models.Genres
import com.task.movies.domain.use_cases.UseCases
import com.task.movies.util.TrailingIconState
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
    private val _selectedGenreId = mutableStateOf(-1)

    fun setGenre(genres: String){
        _selectedGenre.value = genres
    }

    fun setGenreId(genreId: Int){
        _selectedGenreId.value = genreId
    }

    private val _getMovieGenres = mutableStateOf<List<Genres>>(emptyList())
    val movieGenres: State<List<Genres>> = _getMovieGenres

    private val _discoverMovies = mutableStateOf<Flow<PagingData<DiscoverMovies>>>(emptyFlow())
    val discoverMovies: State<Flow<PagingData<DiscoverMovies>>> = _discoverMovies

    val trailingIconState= mutableStateOf(TrailingIconState.READY_TO_DELETE)

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery


    init {
        discoverMovies(null)
        getMovieGenres()
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

    fun search(query: String) {
        viewModelScope.launch {
            _discoverMovies.value = useCases.getDiscoverMoviesUseCase().map { pagingData ->
                pagingData.filter {
                    if(_selectedGenreId.value != -1){
                        it.title.contains(query, ignoreCase = true) && it.genre_ids.contains(_selectedGenreId.value)
                    }else {
                        it.title.contains(query, ignoreCase = true)
                    }
                }
            }.cachedIn(viewModelScope)
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
