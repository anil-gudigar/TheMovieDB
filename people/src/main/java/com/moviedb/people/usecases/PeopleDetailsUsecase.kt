package com.moviedb.people.usecases

import androidx.lifecycle.LiveData
import com.moviedb.core.data.DataWrapper
import com.moviedb.people.data.PeopleDetailRepository
import com.moviedb.people.domian.model.People
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class PeopleDetailsUsecase @Inject constructor(val detailsRepository: PeopleDetailRepository) {
    fun getPeopleDetails(id: String): LiveData<DataWrapper<People>> {
        return detailsRepository.getPeopleDetails(id)
    }
}