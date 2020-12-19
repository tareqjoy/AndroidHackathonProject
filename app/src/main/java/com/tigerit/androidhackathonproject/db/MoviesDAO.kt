package com.tigerit.androidhackathonproject.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tigerit.androidhackathonproject.models.SingleMovie

@Dao
interface MoviesDAO {
    @Insert
    suspend fun insertMovie(movie: SingleMovie)

    @Insert
    suspend fun insertMovie(movies: List<SingleMovie>)

    @Delete
    suspend fun deleteMovie(movie: SingleMovie)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<SingleMovie>>

    @Query("SELECT EXISTS(SELECT * FROM movie_table WHERE id = :id)")
    suspend fun isExist(id: Int): Boolean

}