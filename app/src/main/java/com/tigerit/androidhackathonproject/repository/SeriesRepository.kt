package com.tigerit.androidhackathonproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.tigerit.androidhackathonproject.db.SeriesDAO
import com.tigerit.androidhackathonproject.models.Movies
import com.tigerit.androidhackathonproject.models.Series
import com.tigerit.androidhackathonproject.models.SingleSeries
import com.tigerit.androidhackathonproject.service.PopularSeriesService
import com.tigerit.androidhackathonproject.service.RetrofitAPIInstance

class SeriesRepository(private val dao: SeriesDAO? = null) {
    val seriesAPIService =
        RetrofitAPIInstance.getRetrofitInstance().create(PopularSeriesService::class.java)


    companion object {

        private val listOfPopularSeries = MutableLiveData<Series>()
        private lateinit var seriesRepository: SeriesRepository

        fun getInstance(): SeriesRepository {
            if (!::seriesRepository.isInitialized) {
                seriesRepository = SeriesRepository()
            }
            return seriesRepository
        }
    }

    fun getPopularSeries(
        apiKey: String,
        primaryReleaseYear: Int = 2020,
        sortBy: String = "vote_average.desc",
        page: Int = 1
    ): LiveData<Series?> {
        return liveData {
            val response =
                seriesAPIService.getPopularSeries(apiKey, primaryReleaseYear, sortBy, page)
            emit(response.body())
        }

    }


    suspend fun insertIntoWishList(movie: SingleSeries) {
        dao?.insertSeries(movie)
    }

    suspend fun removeFromWishList(movie: SingleSeries) {
        dao?.deleteSeries(movie)
    }

    suspend fun isInWishList(movie: SingleSeries): Boolean? {
        return dao?.isExist(movie.id)
    }

    fun getWishListedSeries(): LiveData<List<SingleSeries>> {
        return dao?.getAllSeries()!!
    }


}