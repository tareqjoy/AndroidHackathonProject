package com.tigerit.androidhackathonproject.ui.series

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tigerit.androidhackathonproject.R
import com.tigerit.androidhackathonproject.base.BaseActivity
import com.tigerit.androidhackathonproject.databinding.ActivitySeriesDetailsBinding
import com.tigerit.androidhackathonproject.db.AppDatabase
import com.tigerit.androidhackathonproject.models.SingleSeries
import com.tigerit.androidhackathonproject.repository.SeriesRepository
import javax.inject.Inject

class SeriesActivity : BaseActivity() {
    companion object {
        private const val TAG = "SeriesActivity"
        const val EXTRA_MOVIE_DETAIL = "series_detail_extra"
    }

    @Inject
    lateinit var viewModel: SeriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySeriesDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_series_details)


        activityComponent.inject(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        val moviesDAO = AppDatabase.getInstance(this).seriesDAO
        val moviesRepository = SeriesRepository(moviesDAO)

        viewModel.setRepository(moviesRepository)
        viewModel.setMovie(intent.extras!!.getSerializable(EXTRA_MOVIE_DETAIL) as SingleSeries)

    }


}