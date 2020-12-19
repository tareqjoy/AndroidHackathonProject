package com.tigerit.androidhackathonproject.service

import com.tigerit.androidhackathonproject.models.Series
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularSeriesService {
    @GET("/3/discover/tv")
    suspend fun getPopularSeries(
        @Query("api_key") apiKey: String,
        @Query("primary_release_year") primaryReleaseYear: Int = 2020,
        @Query("sort_by") sortBy: String = "vote_average.desc",
        @Query("page") page: Int = 1,
    ): Response<Series>
}