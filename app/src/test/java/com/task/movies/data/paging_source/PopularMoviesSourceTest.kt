package com.task.movies.data.paging_source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.task.movies.data.remote.Api
import com.task.movies.domain.models.PopularMovies
import com.task.movies.domain.models.responses.PopularMoviesApiResponses
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.given
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class PopularMoviesSourceTest {

    private val service = mockk<Api>()
    private lateinit var popularMoviesSource: PopularMoviesSource
    private val popularResponse = mockk<PopularMoviesApiResponses>()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun innit(){
        coEvery { service.getPopularMovies() } returns popularResponse
    }
    @Test
    fun `Popular paging source load - failure - http error`() = runTest {
        val error = RuntimeException("404", Throwable())

        given(service.getPopularMovies()).willThrow(error)

        val expectedResult = PagingSource.LoadResult.Error<Int, PopularMovies>(error)

        Truth.assertThat(expectedResult).isEqualTo(popularMoviesSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        ))
    }
}