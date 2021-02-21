package com.moviedb.discover.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.viewmodel.BaseViewModel
import com.moviedb.discover.usecases.DiscoverMovieUsecase
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DiscoverViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var discoverMovieUsecase: DiscoverMovieUsecase

    fun getMovies(): LiveData<DataWrapper<List<Movie>>> {
        return discoverMovieUsecase.getMovies()
    }
}