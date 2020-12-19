package com.tigerit.androidhackathonproject.models

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tigerit.androidhackathonproject.utils.Constants
import java.io.Serializable

@Entity(tableName = "movie_table")
data class SingleMovie(
    val adult: Boolean?,
    val backdrop_path: String?,
    //val genre_ids: List<Int>,
    @PrimaryKey
    val id: Int,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String,
    val video: Boolean?,
    val vote_average: Int?,
    val vote_count: Int?
) : Serializable {

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

}