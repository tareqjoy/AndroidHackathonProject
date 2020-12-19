package com.tigerit.androidhackathonproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.tigerit.androidhackathonproject.db.MoviesDAO
import com.tigerit.androidhackathonproject.models.Movies
import com.tigerit.androidhackathonproject.models.SingleMovie
import com.tigerit.androidhackathonproject.service.PopularMoviesService
import com.tigerit.androidhackathonproject.service.RetrofitAPIInstance

class MovieRepository(private val dao: MoviesDAO? = null) {
    val moviesAPIService =
        RetrofitAPIInstance.getRetrofitInstance().create(PopularMoviesService::class.java)



    companion object {

        private val listOfPopularMovies = MutableLiveData<Movies>()
        private lateinit var movieRepository: MovieRepository

        fun getInstance(): MovieRepository {
            if (!::movieRepository.isInitialized) {
                movieRepository = MovieRepository()
            }
            return movieRepository
        }
    }

    fun getPopularMovies(
        apiKey: String,
        primaryReleaseYear: Int = 2020,
        sortBy: String = "vote_average.desc",
        page: Int = 1
    ): LiveData<Movies?> {
        return liveData {
            val response =
                moviesAPIService.getPopularMovies(apiKey, primaryReleaseYear, sortBy, page)
            emit(response.body())
        }

    }



    suspend fun insertIntoWishList(movie: SingleMovie) {
        dao?.insertMovie(movie)
    }

    suspend fun removeFromWishList(movie: SingleMovie) {
        dao?.deleteMovie(movie)
    }

    suspend fun isInWishList(movie: SingleMovie): Boolean? {
        return dao?.isExist(movie.id)
    }

    fun getWishListedMovies(): LiveData<List<SingleMovie>> {
        return dao?.getAllMovies()!!
    }


}