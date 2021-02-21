package com.moviedb.app.di

import androidx.lifecycle.ViewModelProvider
import com.moviedb.core.di.ViewModelFactory
import com.moviedb.discover.di.MovieViewModelModule
import com.moviedb.more.di.MoreViewModelModule
import dagger.Binds
import dagger.Module

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Suppress("unused")
@Module(includes = [MovieViewModelModule::class, MoreViewModelModule::class])
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
