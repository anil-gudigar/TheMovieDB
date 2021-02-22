package com.moviedb.people.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moviedb.people.domian.model.People

/**
 * Created by Anil Gudigar on 22,February,2021
 */
@Dao
interface PeopleDao {
    @Query("SELECT * FROM people")
    fun getDiscoverPeopleMovie(): LiveData<List<People>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(peopleList: List<People>)
}