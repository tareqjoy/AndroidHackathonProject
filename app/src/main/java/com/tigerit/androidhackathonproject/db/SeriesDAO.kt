package com.tigerit.androidhackathonproject.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tigerit.androidhackathonproject.models.SingleMovie
import com.tigerit.androidhackathonproject.models.SingleSeries

@Dao
interface SeriesDAO {
    @Insert
    suspend fun insertSeries(series: SingleSeries)

    @Insert
    suspend fun insertSeries(movies: List<SingleSeries>)

    @Delete
    suspend fun deleteSeries(series: SingleSeries)

    @Query("SELECT * FROM series_table")
    fun getAllSeries(): LiveData<List<SingleSeries>>

    @Query("SELECT EXISTS(SELECT * FROM series_table WHERE id = :id)")
    suspend fun isExist(id: Int): Boolean

}