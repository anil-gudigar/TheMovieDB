package com.moviedb.tvshows.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.viewmodel.BaseViewModel
import com.moviedb.tvshows.usecases.DiscoverTVShowsUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowsViewModel @ExperimentalCoroutinesApi @Inject constructor(application: Application) :
    BaseViewModel(application) {

    @Inject
    lateinit var discoverMovieUsecase: DiscoverTVShowsUsecase

    private var currentSearchResult: Flow<PagingData<Movie>>? = null

    fun getMovies(): Flow<PagingData<Movie>> {
        val newResult: Flow<PagingData<Movie>> = discoverMovieUsecase.getMovies()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}