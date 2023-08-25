package com.task.movies.data.repository

import androidx.paging.PagingData
import com.task.movies.domain.models.DiscoverMovies
import com.task.movies.domain.models.MoviesDetails
import com.task.movies.domain.models.responses.GenresApiResponses
import com.task.movies.domain.repository.RemoteDataSource
import com.task.movies.util.Resource
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RemoteSourceTest {
    companion object{
        fun mockRemoteSource(
            resource: Resource<MoviesDetails> = Resource.Loading(),
            resourceOne: Resource<GenresApiResponses> = Resource.Loading(),
            flowOne: Flow<PagingData<DiscoverMovies>> = emptyFlow(),
        ) = object : RemoteDataSource {
            override suspend fun getMovieDetails(filmId: Int) = resource

            override suspend fun getMovieGenres() = resourceOne

            override fun getDiscoverMovies() = flowOne
        }
    }

    @Test
    fun `Get movieDetails starts with Loading, Returns Resource Loading`() = runBlocking {
        val repo = mockRemoteSource(
            resource = Resource.Loading()
        )
        val result = repo.getMovieDetails(2222)
        assert(result is Resource.Loading)
    }

    @Test
    fun `Get movieDetails Success, Return Resource Success + Data`() = runBlocking {
        val moviesDetails = mockk<MoviesDetails>()
        val repo = mockRemoteSource(
            resource = Resource.Success(moviesDetails)
        )
        val result = repo.getMovieDetails(2222)
        assert(result is Resource.Success)
    }

    @Test
    fun `Get Movie Details error, Returns Resource Error`() = runBlocking {
        val moviesDetails = mockk<MoviesDetails>()
        val repo = mockRemoteSource(
            resource = Resource.Error("Error Getting Movie details", moviesDetails)
        )
        val result = repo.getMovieDetails(2222)
        assert(result is Resource.Error && result.message == "Error Getting Movie details")
    }

    @Test
    fun `Get movie genre starts with Loading, Returns Resource Loading`() = runBlocking {
        val repo = mockRemoteSource(
            resourceOne = Resource.Loading()
        )
        val result = repo.getMovieGenres()
        assert(result is Resource.Loading)
    }

    @Test
    fun `Get movie genre Success, Return Resource Success + Data`() = runBlocking {
        val genre = mockk<GenresApiResponses>()
        val repo = mockRemoteSource(
            resourceOne = Resource.Success(genre)
        )
        val result = repo.getMovieGenres()
        assert(result is Resource.Success)
    }

    @Test
    fun `Get movie genre error, Returns Resource Error`() = runBlocking {
        val genre = mockk<GenresApiResponses>()
        val repo = mockRemoteSource(
            resourceOne = Resource.Error("Error Getting Movie details",genre)
        )
        val result = repo.getMovieGenres()
        assert(result is Resource.Error && result.message == "Error Getting Movie details")
    }
}