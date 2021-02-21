package com.moviedb.tvshows.di

import com.moviedb.tvshows.view.DetailsFragment
import com.moviedb.tvshows.view.TvShowsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module
abstract class TVShowBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeTvShowsFragment(): TvShowsFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsFragment
}