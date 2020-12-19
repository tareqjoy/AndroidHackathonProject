package com.tigerit.androidhackathonproject.ui.movie

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tigerit.androidhackathonproject.R
import com.tigerit.androidhackathonproject.base.BaseActivity
import com.tigerit.androidhackathonproject.databinding.ActivityMovieDetailsBinding
import com.tigerit.androidhackathonproject.db.AppDatabase
import com.tigerit.androidhackathonproject.models.SingleMovie
import com.tigerit.androidhackathonproject.repository.MovieRepository
import javax.inject.Inject

class MovieActivity : BaseActivity() {
    companion object {
        private const val TAG = "MovieActivity"
        const val EXTRA_MOVIE_DETAIL = "movie_detail_extra"
    }

    @Inject
    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMovieDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_details)


        activityComponent.inject(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this



        val moviesDAO = AppDatabase.getInstance(this).moviesDAO
        val moviesRepository = MovieRepository(moviesDAO)

        viewModel.setRepository(moviesRepository)
        viewModel.setMovie(intent.extras!!.getSerializable(EXTRA_MOVIE_DETAIL) as SingleMovie)

    }

    private fun loadImage(){

    }
}