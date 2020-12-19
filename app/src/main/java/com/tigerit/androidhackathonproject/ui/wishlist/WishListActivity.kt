package com.tigerit.androidhackathonproject.ui.wishlist

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tigerit.androidhackathonproject.R
import com.tigerit.androidhackathonproject.base.BaseActivity
import com.tigerit.androidhackathonproject.databinding.ActivityWishListBinding
import com.tigerit.androidhackathonproject.db.AppDatabase
import com.tigerit.androidhackathonproject.repository.MovieRepository
import com.tigerit.androidhackathonproject.repository.SeriesRepository
import com.tigerit.androidhackathonproject.ui.adapters.MoviesAdapter
import com.tigerit.androidhackathonproject.ui.adapters.SeriesAdapter
import com.tigerit.androidhackathonproject.ui.helpers.ActivityNavigator
import javax.inject.Inject

class WishListActivity : BaseActivity() {
    companion object {
        private const val TAG = "WishListActivity"
    }

    @Inject
    lateinit var viewModel: WishListViewModel

    @Inject
    lateinit var activityNavigator: ActivityNavigator

    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularSeriesAdapter: SeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityWishListBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_wish_list)
        activityComponent.inject(this)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        popularMoviesAdapter = MoviesAdapter(this) {
            activityNavigator.toMovieDetails(it)
        }

        popularSeriesAdapter = SeriesAdapter(this) {
            activityNavigator.toSeriesDetails(it)
        }


        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = popularMoviesAdapter

        val moviesDAO = AppDatabase.getInstance(this).moviesDAO
        val moviesRepository = MovieRepository(moviesDAO)

        viewModel.setMoviesRepository(moviesRepository)

        binding.rvTvSeries.layoutManager = LinearLayoutManager(this)
        binding.rvTvSeries.adapter = popularSeriesAdapter

        val seriesDAO = AppDatabase.getInstance(this).seriesDAO
        val seriesRepository = SeriesRepository(seriesDAO)


        viewModel.setSeriesRepository(seriesRepository)


    }

    override fun onStart() {
        super.onStart()
        viewModel.loadWishListedMovies()?.observe(this, Observer {
            if (it != null) {
                popularMoviesAdapter.setMovies(it)
            }
        })

        viewModel.loadWishListedSeries()?.observe(this, Observer {
            if (it != null) {
                popularSeriesAdapter.setSeries(it)
            }
        })
    }
}