package com.moviedb.people.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moviedb.people.data.converter.Converters
import com.moviedb.people.data.local.PeopleDao
import com.moviedb.people.data.local.PeopleDetailsDao
import com.moviedb.people.domian.model.KnownFor
import com.moviedb.people.domian.model.People

/**
 * Created by Anil Gudigar on 22,February,2021
 */
@Database(
        entities = [People::class, KnownFor::class],
        version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PeopleDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao

    abstract fun peopleDetailsDao(): PeopleDetailsDao

    companion object {
        @Volatile
        private var instance: PeopleDatabase? = null

        fun getInstance(context: Context): PeopleDatabase {
            return instance ?: synchronized(this) {
                instance
                        ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): PeopleDatabase {
            return Room.databaseBuilder(context, PeopleDatabase::class.java, "people-db")
                    .addCallback(object : RoomDatabase.Callback() {
                    })
                    .build()
        }
    }
}
