package com.moviedb.discover.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moviedb.common.domain.model.Genres
import com.moviedb.common.domain.model.Movie
import com.moviedb.common.converter.Converters
import com.moviedb.discover.data.local.DetailsDao
import com.moviedb.discover.data.local.DiscoverDao

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Database(
    entities = [Movie::class, Genres::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun discoverDao(): DiscoverDao

    abstract fun detailsDao(): DetailsDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MovieDatabase {
            return Room.databaseBuilder(context, MovieDatabase::class.java, "movie-db")
                .addCallback(object : RoomDatabase.Callback() {
                })
                .build()
        }
    }
}
