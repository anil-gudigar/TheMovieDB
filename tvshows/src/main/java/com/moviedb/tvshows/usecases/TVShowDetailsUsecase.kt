package com.moviedb.tvshows.usecases

import androidx.lifecycle.LiveData
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.data.DataWrapper
import com.moviedb.tvshows.data.DetailsRepository
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class TVShowDetailsUsecase @Inject constructor(val detailsRepository: DetailsRepository) {
    fun getMovieDetails(id: String): LiveData<DataWrapper<Movie>> {
        return detailsRepository.getMovieDetails(id)
    }
}