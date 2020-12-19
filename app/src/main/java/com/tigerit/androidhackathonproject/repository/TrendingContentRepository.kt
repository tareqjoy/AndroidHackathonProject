package com.tigerit.androidhackathonproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.tigerit.androidhackathonproject.db.SeriesDAO
import com.tigerit.androidhackathonproject.models.Movies
import com.tigerit.androidhackathonproject.models.Series
import com.tigerit.androidhackathonproject.models.SingleSeries
import com.tigerit.androidhackathonproject.models.TrendingContents
import com.tigerit.androidhackathonproject.service.PopularSeriesService
import com.tigerit.androidhackathonproject.service.RetrofitAPIInstance
import com.tigerit.androidhackathonproject.service.TrendingContentService

class TrendingContentRepository() {
    val trndingAPIService =
        RetrofitAPIInstance.getRetrofitInstance().create(TrendingContentService::class.java)


    companion object {


        private lateinit var seriesRepository: TrendingContentRepository

        fun getInstance(): TrendingContentRepository {
            if (!::seriesRepository.isInitialized) {
                seriesRepository = TrendingContentRepository()
            }
            return seriesRepository
        }
    }

    fun getTrendingContent(
        apiKey: String,
        page: Int = 1
    ): LiveData<TrendingContents?> {
        return liveData {
            val response =
                    trndingAPIService.getTrendingContent(apiKey, page)
            emit(response.body())
        }

    }


}