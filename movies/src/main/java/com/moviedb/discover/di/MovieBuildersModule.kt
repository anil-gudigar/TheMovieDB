package com.moviedb.discover.di

import com.moviedb.discover.view.DetailsFragment
import com.moviedb.discover.view.DiscoverFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module
abstract class MovieBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeDiscoverFragment(): DiscoverFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsFragment
}