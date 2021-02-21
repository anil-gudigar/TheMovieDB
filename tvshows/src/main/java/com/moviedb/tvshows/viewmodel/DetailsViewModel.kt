package com.moviedb.tvshows.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.viewmodel.BaseViewModel
import com.moviedb.tvshows.usecases.TVShowDetailsUsecase
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DetailsViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var movieDetailsUsecase: TVShowDetailsUsecase

    lateinit var id: String

    fun getMovieDetails(): LiveData<DataWrapper<Movie>> {
        return movieDetailsUsecase.getMovieDetails(id)
    }

}