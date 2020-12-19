package com.tigerit.androidhackathonproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tigerit.androidhackathonproject.models.SingleMovie
import com.tigerit.androidhackathonproject.models.SingleSeries

@Database(entities = [SingleMovie::class, SingleSeries::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val moviesDAO: MoviesDAO
    abstract val seriesDAO: SeriesDAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = instance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }
                return instance
            }

        }
    }
}