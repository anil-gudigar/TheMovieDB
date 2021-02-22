package com.moviedb.people.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.moviedb.core.viewmodel.BaseViewModel
import com.moviedb.people.domian.model.People
import com.moviedb.people.usecases.DiscoverPeopleUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleViewModel @ExperimentalCoroutinesApi @Inject constructor(application: Application) :
        BaseViewModel(application) {

    @Inject
    lateinit var discoverPeopleUsecase: DiscoverPeopleUsecase

    private var currentSearchResult: Flow<PagingData<People>>? = null

    fun getPeople(): Flow<PagingData<People>> {
        val newResult: Flow<PagingData<People>> = discoverPeopleUsecase.getPeople()
                .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}