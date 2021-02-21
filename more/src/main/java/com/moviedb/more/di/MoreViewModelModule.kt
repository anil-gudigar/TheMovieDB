package com.moviedb.more.di

import androidx.lifecycle.ViewModel
import com.moviedb.core.di.ViewModelKey
import com.moviedb.more.viewmodel.MoreViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Anil Gudigar on 22,February,2021
 */
@Module
abstract class MoreViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoreViewModel::class)
    abstract fun bindMoreViewModel(viewModel: MoreViewModel): ViewModel
}