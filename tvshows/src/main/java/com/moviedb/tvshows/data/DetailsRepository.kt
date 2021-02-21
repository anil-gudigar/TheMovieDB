package com.moviedb.tvshows.data

import androidx.lifecycle.LiveData
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.data.resultLiveData
import com.moviedb.tvshows.data.local.DetailsDao
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DetailsRepository @Inject constructor(
    private val dao: DetailsDao,
    private val remoteSource: DetailsRemoteDataSource
) {

    fun getMovieDetails(id: String): LiveData<DataWrapper<Movie>> {
        return resultLiveData(
            databaseQuery = { dao.getMovieDetails(id) },
            networkCall = { remoteSource.fetchData(id) },
            saveCallResult = { it.let { dao.insert(it) } })
    }

}