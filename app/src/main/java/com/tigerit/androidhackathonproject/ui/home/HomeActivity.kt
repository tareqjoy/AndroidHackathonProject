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

        /*val moviesService = RetrofitInstance.getRetrofitInstance().create(PopularMoviesService::class.java)

        val responseLiveData:LiveData<Response<Movies>> = liveData {
            val response = moviesService.getPopularMovies(apiKey = "1a97f3b8d5deee1d649c0025f3acf75c")
            emit(response)
        }
        responseLiveData.observe(this, Observer { response ->
            response.body()?.results?.forEach { singleMovie ->
                Log.d(TAG, "Movie: ${singleMovie.title}")
            }

        })*/
    }
}