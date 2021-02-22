package com.moviedb.people.usecases

import androidx.paging.PagingData
import com.moviedb.people.data.PeopleDiscoverRepository
import com.moviedb.people.domian.model.People
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class DiscoverPeopleUsecase @Inject constructor(val discoverRepository: PeopleDiscoverRepository) {
    fun getPeople(): Flow<PagingData<People>> {
        return discoverRepository.getPeople()
    }
}