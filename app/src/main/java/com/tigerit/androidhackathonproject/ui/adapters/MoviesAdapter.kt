package com.tigerit.androidhackathonproject.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tigerit.androidhackathonproject.R
import com.tigerit.androidhackathonproject.databinding.ItemMovieBinding
import com.tigerit.androidhackathonproject.models.SingleMovie

class MoviesAdapter(val context: Context, val clickListener: (SingleMovie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: SingleMovie, position: Int) {
            binding.movie = movie
            //binding.showDivider = !isLastItem(position)
            binding.executePendingBindings()
        }
    }

    private var movies = ArrayList<SingleMovie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_movie, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, position)
        holder.binding.root.setOnClickListener { clickListener(movie) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setMovies(movies: List<SingleMovie>) {
        this.movies = movies as ArrayList<SingleMovie>
        notifyDataSetChanged()
    }


}