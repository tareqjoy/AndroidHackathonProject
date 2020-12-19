package com.tigerit.androidhackathonproject.ui.series

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tigerit.androidhackathonproject.base.BaseViewModel
import com.tigerit.androidhackathonproject.models.SingleSeries
import com.tigerit.androidhackathonproject.repository.SeriesRepository
import com.tigerit.androidhackathonproject.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeriesViewModel @Inject constructor() : BaseViewModel() {

    private var repository: SeriesRepository? = null
    private var series: SingleSeries? = null
    val seriesTitle = MutableLiveData<String>()
    val addedToWishList = MutableLiveData<Boolean>()
    val imageUrl = MutableLiveData<String>()

    companion object {
        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun setImageUrl(imageView: ImageView, url: String) {
            Glide.with(imageView)
                .load(Constants.URL_IMAGE_POSTER + url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }

    init {
        addedToWishList.value = true
    }

    fun onWishListChange() {
        if (addedToWishList.value!!) {
            viewModelScope.launch {
                series?.let { repository?.insertIntoWishList(it) }
            }
        } else {
            viewModelScope.launch {
                series?.let { repository?.removeFromWishList(it) }
            }
        }


        addedToWishList.value = !addedToWishList.value!!

    }

    fun setMovie(series: SingleSeries) {
        seriesTitle.value = series.name
        imageUrl.value = series.poster_path
        this.series = series

        viewModelScope.launch {
            addedToWishList.value = !repository?.isInWishList(series)!!
        }
    }

    fun setRepository(seriesRepository: SeriesRepository) {
        this.repository = seriesRepository
    }


}