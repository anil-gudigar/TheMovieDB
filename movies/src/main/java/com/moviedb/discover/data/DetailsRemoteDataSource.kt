package com.moviedb.discover.data

import com.moviedb.core.BuildConfig
import com.moviedb.core.api.BaseDataSource
import com.moviedb.discover.data.remote.DiscoverService
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DetailsRemoteDataSource @Inject constructor(private val service: DiscoverService) : BaseDataSource() {

    suspend fun fetchData(id: String) = execute {
        service.getMovie(id,
                BuildConfig.API_DEVELOPER_TOKEN, "en")
    }

}