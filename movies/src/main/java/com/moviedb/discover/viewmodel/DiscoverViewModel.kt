package com.moviedb.discover.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.viewmodel.BaseViewModel
import com.moviedb.discover.usecases.DiscoverMovieUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DiscoverViewModel @ExperimentalCoroutinesApi @Inject constructor(application: Application) :
    BaseViewModel(application) {

    @Inject
    lateinit var discoverMovieUsecase: DiscoverMovieUsecase

    private var currentSearchResult: Flow<PagingData<Movie>>? = null

    fun getMovies(): Flow<PagingData<Movie>> {
        val newResult: Flow<PagingData<Movie>> = discoverMovieUsecase.getMovies()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}