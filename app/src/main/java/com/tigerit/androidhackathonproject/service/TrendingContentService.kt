package com.tigerit.androidhackathonproject.service

import com.tigerit.androidhackathonproject.models.Series
import com.tigerit.androidhackathonproject.models.TrendingContents
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingContentService {
    @GET("/3/trending/all/week")
    suspend fun getTrendingContent(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): Response<TrendingContents>
}