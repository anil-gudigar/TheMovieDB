package com.moviedb.people.data

import com.moviedb.core.BuildConfig
import com.moviedb.core.api.BaseDataSource
import com.moviedb.people.data.remote.PeopleService
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class PeopleDetialRemoteDataSource @Inject constructor(private val service: PeopleService) : BaseDataSource() {

    suspend fun fetchData(id: String) = execute {
        service.getPerson(id, BuildConfig.API_DEVELOPER_TOKEN, "en")
    }

}