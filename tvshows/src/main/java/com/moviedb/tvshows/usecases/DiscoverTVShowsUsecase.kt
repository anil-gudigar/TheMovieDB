package com.moviedb.tvshows.usecases

import androidx.paging.PagingData
import com.moviedb.common.domain.model.Movie
import com.moviedb.tvshows.data.DiscoverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DiscoverTVShowsUsecase @Inject constructor(val discoverRepository: DiscoverRepository) {
    fun getMovies(): Flow<PagingData<Movie>> {
        return discoverRepository.getMovies()
    }
}