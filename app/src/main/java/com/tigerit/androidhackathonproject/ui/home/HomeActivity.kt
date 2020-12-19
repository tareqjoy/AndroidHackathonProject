package com.tigerit.androidhackathonproject.ui.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tigerit.androidhackathonproject.R
import com.tigerit.androidhackathonproject.base.BaseActivity
import com.tigerit.androidhackathonproject.databinding.ActivityHomeBinding
import com.tigerit.androidhackathonproject.ui.adapters.MoviesAdapter
import com.tigerit.androidhackathonproject.ui.adapters.SeriesAdapter
import com.tigerit.androidhackathonproject.ui.adapters.TrendingContentAdapter
import com.tigerit.androidhackathonproject.ui.helpers.ActivityNavigator
import javax.inject.Inject


class HomeActivity : BaseActivity() {
    companion object {
        private const val TAG = "HomeActivity"
    }

    @Inject
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var activityNavigator: ActivityNavigator
    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularSeriesAdapter: SeriesAdapter
    private lateinit var trendingContentAdapter: TrendingContentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)


        activityComponent.inject(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.activityNavigator = activityNavigator

        popularMoviesAdapter = MoviesAdapter(this) {
            activityNavigator.toMovieDetails(it)
        }

        binding.rvPopularMovies.layoutManager = LinearLayoutManager(this)
        binding.rvPopularMovies.adapter = popularMoviesAdapter

        viewModel.loadPopularMovies().observe(this, Observer {
            if (it != null) {
                popularMoviesAdapter.setMovies(it.results)
            }

        })

        popularSeriesAdapter = SeriesAdapter(this) {
            activityNavigator.toSeriesDetails(it)
        }

        binding.rvPopularTvSeries.layoutManager = LinearLayoutManager(this)
        binding.rvPopularTvSeries.adapter = popularSeriesAdapter

        viewModel.loadPopularSeries().observe(this, Observer {
            if (it != null) {
                popularSeriesAdapter.setSeries(it.results)
            }

        })

        trendingContentAdapter = TrendingContentAdapter(this) {
            //activityNavigator.toSeriesDetails(it)
        }

        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        binding.rvTrendingContents.layoutManager = layoutManager
        binding.rvTrendingContents.adapter = trendingContentAdapter

        viewModel.loadTrendingContents().observe(this, Observer {
            if (it != null) {
                trendingContentAdapter.setSeries(it.results)
            }

        })

    }
}