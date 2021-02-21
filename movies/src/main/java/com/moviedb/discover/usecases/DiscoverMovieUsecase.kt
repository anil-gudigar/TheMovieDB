package com.moviedb.discover.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.data.DataWrapper
import com.moviedb.discover.data.DiscoverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DiscoverMovieUsecase @Inject constructor(val discoverRepository: DiscoverRepository) {
    fun getMovies(): Flow<PagingData<Movie>> {
        return discoverRepository.getMovies()
    }
}