package com.tigerit.androidhackathonproject.di.activity

import com.tigerit.androidhackathonproject.ui.home.HomeActivity
import com.tigerit.androidhackathonproject.di.activity.modules.ActivityModule
import com.tigerit.androidhackathonproject.ui.movie.MovieActivity
import com.tigerit.androidhackathonproject.ui.series.SeriesActivity
import com.tigerit.androidhackathonproject.ui.wishlist.WishListActivity
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent
{
    fun inject(homeActivity: HomeActivity)
    fun inject(movieActivity: MovieActivity)
    fun inject(wishListActivity: WishListActivity)
    fun inject(seriesActivity: SeriesActivity)
}