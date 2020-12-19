package com.tigerit.androidhackathonproject.ui.wishlist

import androidx.lifecycle.LiveData
import com.tigerit.androidhackathonproject.base.BaseViewModel
import com.tigerit.androidhackathonproject.models.SingleMovie
import com.tigerit.androidhackathonproject.models.SingleSeries
import com.tigerit.androidhackathonproject.repository.MovieRepository
import com.tigerit.androidhackathonproject.repository.SeriesRepository
import javax.inject.Inject

class WishListViewModel @Inject constructor() : BaseViewModel() {

    private var moviesRepository: MovieRepository? = null
    private var seriesRepository: SeriesRepository? = null


    companion object {
        private const val TAG = "WishListViewModel"
    }

    fun loadWishListedMovies(): LiveData<List<SingleMovie>>? {
        return moviesRepository?.getWishListedMovies()
    }

    fun loadWishListedSeries(): LiveData<List<SingleSeries>>? {
        return seriesRepository?.getWishListedSeries()
    }

    fun setMoviesRepository(movieRepository: MovieRepository) {
        this.moviesRepository = movieRepository
    }

    fun setSeriesRepository(seriesRepository: SeriesRepository) {
        this.seriesRepository = seriesRepository
    }

}