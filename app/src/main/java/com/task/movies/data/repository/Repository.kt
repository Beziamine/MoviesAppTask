package com.task.movies.data.repository

import androidx.paging.PagingData
import com.task.movies.domain.models.DiscoverMovies
import com.task.movies.domain.models.MoviesDetails
import com.task.movies.domain.models.MyList
import com.task.movies.domain.models.responses.GenresApiResponses
import com.task.movies.domain.repository.DataStoreOperations
import com.task.movies.domain.repository.LocalDataSource
import com.task.movies.domain.repository.RemoteDataSource
import com.task.movies.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStoreOperations: DataStoreOperations
){
    suspend fun getMoviesDetails(filmId:Int):Resource<MoviesDetails>{
        return remote.getMovieDetails(filmId= filmId)
    }
    suspend fun getMovieGenres(): Resource<GenresApiResponses> {
       return remote.getMovieGenres()
    }
    fun getDiscoverMovies(): Flow<PagingData<DiscoverMovies>>{
        return remote.getDiscoverMovies()
    }
    fun getMyList(): Flow<List<MyList>>{
        return local.getMyList()
    }
    fun getSelectedFromMyList(listId: Int):MyList{
        return local.getSelectedFromMyList(listId = listId)
    }
    suspend fun addToMyList(myList: MyList){
        return local.addToMyList(myList = myList)
    }
     suspend fun ifExists(listId:Int):Int{
        return local.ifExists(listId)
    }
    suspend fun deleteOneFromMyList(myList: Int){
        return local.deleteOneFromMyList(myList = myList)
    }
    suspend fun deleteAllContentFromMyList(){
        return local.deleteAllContentFromMyList()
    }
}