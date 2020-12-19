package com.tigerit.androidhackathonproject

import com.tigerit.androidhackathonproject.models.Movies
import com.tigerit.androidhackathonproject.models.Series
import com.tigerit.androidhackathonproject.service.PopularMoviesService
import com.tigerit.androidhackathonproject.service.PopularSeriesService
import com.tigerit.androidhackathonproject.service.RetrofitAPIInstance
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class MainUnitTest {
    @Test
    fun getMovieSuccess() {
        val apiEndpoints = RetrofitAPIInstance.getRetrofitInstance().create(PopularMoviesService::class.java)
        GlobalScope.launch {
            val call: Response<Movies> = apiEndpoints.getPopularMovies("1a97f3b8d5deee1d649c0025f3acf75c")
            try {
                assertTrue(call.isSuccessful)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    @Test
    fun getSeriesSuccess() {
        val apiEndpoints = RetrofitAPIInstance.getRetrofitInstance().create(PopularSeriesService::class.java)
        GlobalScope.launch {
            val call: Response<Series> = apiEndpoints.getPopularSeries("1a97f3b8d5deee1d649c0025f3acf75c")
            try {
                assertTrue(call.isSuccessful)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}