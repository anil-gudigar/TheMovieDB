package com.moviedb.people.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.moviedb.core.data.DataWrapper
import com.moviedb.core.viewmodel.BaseViewModel
import com.moviedb.people.domian.model.People
import com.moviedb.people.usecases.PeopleDetailsUsecase
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class PeopleDetailsViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var peopleDetailsUsecase: PeopleDetailsUsecase

    lateinit var id: String

    fun getPeopleDetails(): LiveData<DataWrapper<People>> {
        return peopleDetailsUsecase.getPeopleDetails(id)
    }

}