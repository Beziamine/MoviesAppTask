package com.task.movies.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.task.movies.data.paging_source.DiscoverMoviesSource
import com.task.movies.data.remote.Api
import com.task.movies.domain.models.DiscoverMovies
import com.task.movies.domain.models.MoviesDetails
import com.task.movies.domain.models.responses.GenresApiResponses
import com.task.movies.domain.repository.RemoteDataSource
import com.task.movies.util.Constants.ITEMS_PER_PAGE
import com.task.movies.util.Resource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImp(
    private val api: Api
):RemoteDataSource {
    override suspend fun getMovieDetails(filmId: Int):Resource<MoviesDetails> {
        val response = try {
            api.getMovieDetails(filmId)
        }catch (e:Exception){
            return Resource.Error("Unknown Error")
        }
        Log.d("MovieDetails", "$response")
        return Resource.Success(response)
    }

    override suspend fun getMovieGenres(): Resource<GenresApiResponses> {
        val response = try {
            api.getMovieGenres()
        }catch (e: Exception){
            return Resource.Error("Unknown Error")
        }
        return Resource.Success(response)
    }


    override fun getDiscoverMovies(): Flow<PagingData<DiscoverMovies>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                DiscoverMoviesSource(api = api)
            }
        ).flow
    }
}