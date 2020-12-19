package com.tigerit.androidhackathonproject.ui.helpers

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.tigerit.androidhackathonproject.models.SingleMovie
import com.tigerit.androidhackathonproject.models.SingleSeries
import com.tigerit.androidhackathonproject.ui.movie.MovieActivity
import com.tigerit.androidhackathonproject.ui.series.SeriesActivity
import com.tigerit.androidhackathonproject.ui.wishlist.WishListActivity


class ActivityNavigator(private val activity: Activity) {
    fun toMovieDetails(detail: SingleMovie) {
        val intent = Intent(activity, MovieActivity::class.java)
        intent.putExtra(MovieActivity.EXTRA_MOVIE_DETAIL, detail)
        activity.startActivity(intent)
    }

    fun toSeriesDetails(detail: SingleSeries) {
        val intent = Intent(activity, SeriesActivity::class.java)
        intent.putExtra(SeriesActivity.EXTRA_MOVIE_DETAIL, detail)
        activity.startActivity(intent)
    }

    fun toWishList() {
        Log.d("komu", "toWishList: ")
        val intent = Intent(activity, WishListActivity::class.java)
        activity.startActivity(intent)
    }
}