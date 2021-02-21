package com.moviedb.discover.usecases

import androidx.lifecycle.LiveData
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.data.DataWrapper
import com.moviedb.discover.data.DiscoverRepository
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DiscoverMovieUsecase @Inject constructor(val discoverRepository: DiscoverRepository) {
    fun getMovies(): LiveData<DataWrapper<List<Movie>>> {
        return discoverRepository.getMovies()
    }
}