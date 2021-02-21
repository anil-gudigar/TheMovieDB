package com.moviedb.tvshows.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moviedb.common.domain.model.Genres
import com.moviedb.common.domain.model.Movie
import com.moviedb.common.converter.Converters
import com.moviedb.tvshows.data.local.DetailsDao
import com.moviedb.tvshows.data.local.DiscoverDao

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Database(
    entities = [Movie::class, Genres::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TVShowDatabase : RoomDatabase() {

    abstract fun discoverDao(): DiscoverDao

    abstract fun detailsDao(): DetailsDao

    companion object {
        @Volatile
        private var instance: TVShowDatabase? = null

        fun getInstance(context: Context): TVShowDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): TVShowDatabase {
            return Room.databaseBuilder(context, TVShowDatabase::class.java, "tvshow-db")
                .addCallback(object : RoomDatabase.Callback() {
                })
                .build()
        }
    }
}
