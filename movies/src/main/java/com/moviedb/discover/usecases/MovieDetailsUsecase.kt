package com.moviedb.discover.usecases

import androidx.lifecycle.LiveData
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.data.DataWrapper
import com.moviedb.discover.data.DetailsRepository
import com.moviedb.discover.data.DiscoverRepository
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class MovieDetailsUsecase @Inject constructor(val detailsRepository: DetailsRepository) {
    fun getMovieDetails(id: String): LiveData<DataWrapper<Movie>> {
        return detailsRepository.getMovieDetails(id)
    }
}