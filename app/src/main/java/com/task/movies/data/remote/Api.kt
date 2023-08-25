package com.task.movies.data.remote

import com.task.movies.domain.models.*
import com.task.movies.domain.models.responses.*
import com.task.movies.util.Constants.API_KEY
import com.task.movies.util.Constants.INCLUDE_ADULT
import com.task.movies.util.Constants.LANGUAGE
import com.task.movies.util.Constants.POPULARITY
import com.task.movies.util.Constants.STARTING_PAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): GenresApiResponses

    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("page") page :Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("include_adult") includeAdult :Boolean = INCLUDE_ADULT,
        @Query("sort_by") sortBy :String = POPULARITY
    ): DiscoverMoviesApiResponses

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") filmId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): MoviesDetails
}