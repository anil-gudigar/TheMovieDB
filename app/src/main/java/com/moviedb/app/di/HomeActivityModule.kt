package com.moviedb.app.di

import com.moviedb.app.HomeActivity
import com.moviedb.discover.di.MovieBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Suppress("unused")
@Module
abstract class HomeActivityModule {
    @ContributesAndroidInjector(modules = [MovieBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}
