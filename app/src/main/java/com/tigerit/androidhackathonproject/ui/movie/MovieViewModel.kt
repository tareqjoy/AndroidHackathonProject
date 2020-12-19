package com.tigerit.androidhackathonproject.ui.movie

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tigerit.androidhackathonproject.base.BaseViewModel
import com.tigerit.androidhackathonproject.models.SingleMovie
import com.tigerit.androidhackathonproject.repository.MovieRepository
import com.tigerit.androidhackathonproject.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor() : BaseViewModel() {

    private var repository: MovieRepository? = null
    private var movie: SingleMovie? = null
    val movieTitle = MutableLiveData<String>()
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
                movie?.let { repository?.insertIntoWishList(it) }
            }
        } else {
            viewModelScope.launch {
                movie?.let { repository?.removeFromWishList(it) }
            }
        }


        addedToWishList.value = !addedToWishList.value!!

    }

    fun setMovie(movie: SingleMovie) {
        movieTitle.value = movie.title
        imageUrl.value = movie.poster_path
        this.movie = movie

        viewModelScope.launch {
            addedToWishList.value = !repository?.isInWishList(movie)!!
        }
    }

    fun setRepository(movieRepository: MovieRepository) {
        this.repository = movieRepository
    }


}