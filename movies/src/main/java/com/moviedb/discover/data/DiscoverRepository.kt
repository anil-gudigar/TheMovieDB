package com.moviedb.discover.data

import androidx.lifecycle.LiveData
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.data.resultLiveData
import com.moviedb.discover.data.local.DiscoverDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class DiscoverRepository @Inject constructor(
    private val dao: DiscoverDao,
    private val remoteSource: DiscoverRemoteDataSource
) {

    fun getMovies(): LiveData<DataWrapper<List<Movie>>> {
        return resultLiveData(
            databaseQuery = { dao.getDiscoverMovie() },
            networkCall = { remoteSource.fetchData() },
            saveCallResult = { it.results?.let { it1 -> dao.insertAll(it1) } })
    }


}