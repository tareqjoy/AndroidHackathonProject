package com.tigerit.androidhackathonproject.service

import com.tigerit.androidhackathonproject.models.Movies
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularMoviesService {
    @GET("/3/discover/movie")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("primary_release_year") primaryReleaseYear: Int = 2020,
        @Query("sort_by") sortBy: String = "vote_average.desc",
        @Query("page") page: Int = 1,
    ): Response<Movies>

}