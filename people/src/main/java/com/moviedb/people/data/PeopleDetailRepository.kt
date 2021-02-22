package com.moviedb.people.data

import androidx.lifecycle.LiveData
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.data.resultLiveData
import com.moviedb.people.data.local.PeopleDetailsDao
import com.moviedb.people.domian.model.People
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class PeopleDetailRepository @Inject constructor(private val dao: PeopleDetailsDao,
                                                 private val remoteSource: PeopleDetialRemoteDataSource) {

    fun getPeopleDetails(id: String): LiveData<DataWrapper<People>> {
        return resultLiveData(
                databaseQuery = { dao.getPeopleDetails(id) },
                networkCall = { remoteSource.fetchData(id) },
                saveCallResult = { it.let { dao.insert(it) } })
    }

}