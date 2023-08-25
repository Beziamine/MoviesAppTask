package com.task.movies.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task.movies.data.remote.Api
import com.task.movies.domain.models.DiscoverMovies
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DiscoverMoviesSource @Inject constructor(
    private val api: Api
): PagingSource<Int, DiscoverMovies>() {
    override fun getRefreshKey(state: PagingState<Int, DiscoverMovies>): Int? {
       return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovies> {
        return try {
            val nextPage = params.key ?: 1
            val discoverMovies = api.getDiscoverMovies(nextPage)
            LoadResult.Page(
                data = discoverMovies.searches,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (discoverMovies.searches.isEmpty()) null else discoverMovies.page + 1
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}