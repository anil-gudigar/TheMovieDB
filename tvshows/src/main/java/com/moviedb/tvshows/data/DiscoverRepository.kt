package com.moviedb.tvshows.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.moviedb.common.domain.model.Movie
import com.moviedb.tvshows.data.local.DiscoverDao
import kotlinx.coroutines.flow.Flow
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
    companion object {
        private const val NETWORK_PAGE_SIZE = 1000

    }
    //Moved to page and flow
    /*fun getMovies(page:Int): LiveData<DataWrapper<List<Movie>>> {
           return resultLiveData(
               databaseQuery = { dao.getDiscoverMovie() },
               networkCall = { remoteSource.fetchData(page) },
               saveCallResult = { it.results?.let { it1 -> dao.insertAll(it1) } })
       }*/

    //TODO: Save in local DB
    fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { remoteSource }
        ).flow
    }
}