package com.moviedb.people.di

import androidx.lifecycle.ViewModel
import com.moviedb.core.di.ViewModelKey
import com.moviedb.people.viewmodel.PeopleDetailsViewModel
import com.moviedb.people.viewmodel.PeopleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module
abstract class PeopleViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PeopleViewModel::class)
    abstract fun bindPeopleViewModel(viewModel: PeopleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PeopleDetailsViewModel::class)
    abstract fun bindPeopleDetailsViewModel(viewModel: PeopleDetailsViewModel): ViewModel

}