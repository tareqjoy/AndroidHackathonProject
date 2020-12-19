package com.tigerit.androidhackathonproject.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tigerit.androidhackathonproject.utils.Constants
import java.io.Serializable

@Entity(tableName = "series_table")
data class SingleSeries(
    val backdrop_path: String?,
    val first_air_date: String?,
  //  val genre_ids: List<Int>,
    @PrimaryKey
    val id: Int,
    val name: String,
    //val origin_country: List<String>,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
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