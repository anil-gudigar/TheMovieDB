package com.moviedb.discover.di

import androidx.lifecycle.ViewModel
import com.moviedb.core.di.ViewModelKey
import com.moviedb.discover.viewmodel.DetailsViewModel
import com.moviedb.discover.viewmodel.DiscoverViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module
abstract class MovieViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    abstract fun bindDiscoverViewModel(viewModel: DiscoverViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

}