package com.tigerit.androidhackathonproject.ui.home

import androidx.lifecycle.LiveData
import com.tigerit.androidhackathonproject.base.BaseViewModel
import com.tigerit.androidhackathonproject.models.Movies
import com.tigerit.androidhackathonproject.models.Series
import com.tigerit.androidhackathonproject.models.TrendingContents
import com.tigerit.androidhackathonproject.repository.MovieRepository
import com.tigerit.androidhackathonproject.repository.SeriesRepository
import com.tigerit.androidhackathonproject.repository.TrendingContentRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {

    private val popularMoviesRepository = MovieRepository.getInstance()
    private val popularSeriesRepository = SeriesRepository.getInstance()
    private val trendingContentRepository = TrendingContentRepository.getInstance()

    companion object {
        private const val TAG = "HomeViewModel"
    }

    fun loadPopularMovies(): LiveData<Movies?> {
        return popularMoviesRepository.getPopularMovies(apiKey = "1a97f3b8d5deee1d649c0025f3acf75c")
    }

    fun loadPopularSeries(): LiveData<Series?> {
        return popularSeriesRepository.getPopularSeries(apiKey = "1a97f3b8d5deee1d649c0025f3acf75c")
    }

    fun loadTrendingContents(): LiveData<TrendingContents?> {
        return trendingContentRepository.getTrendingContent(apiKey = "1a97f3b8d5deee1d649c0025f3acf75c")
    }

}