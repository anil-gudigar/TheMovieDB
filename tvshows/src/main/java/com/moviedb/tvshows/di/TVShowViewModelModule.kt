package com.moviedb.tvshows.di

import androidx.lifecycle.ViewModel
import com.moviedb.core.di.ViewModelKey
import com.moviedb.tvshows.viewmodel.DetailsViewModel
import com.moviedb.tvshows.viewmodel.TvShowsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module
abstract class TVShowViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TvShowsViewModel::class)
    abstract fun bindTvShowsViewModel(viewModel: TvShowsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

}