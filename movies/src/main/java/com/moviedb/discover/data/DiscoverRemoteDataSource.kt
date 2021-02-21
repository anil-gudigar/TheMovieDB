package com.moviedb.discover.data

import com.moviedb.core.BuildConfig
import com.moviedb.core.api.BaseDataSource
import com.moviedb.discover.data.remote.DiscoverService
import javax.inject.Inject

/**
 * Works with the Discover to get data.
 */
class DiscoverRemoteDataSource @Inject constructor(private val service: DiscoverService) :
    BaseDataSource() {

    suspend fun fetchData() = execute {
        service.getMoviesList(
            BuildConfig.API_DEVELOPER_TOKEN,
            "en",
            "popularity.desc",
            "false",
            "false",
            1
        )
    }

}