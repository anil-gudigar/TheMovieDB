package com.moviedb.people.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moviedb.common.domain.model.Movie
import com.moviedb.people.domian.model.People

/**
 * Created by Anil Gudigar on 22,February,2021
 */
@Dao
interface PeopleDetailsDao {
    @Query("SELECT * FROM people WHERE id = :id")
    fun getPeopleDetails(id: String): LiveData<People>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(people: People)
}