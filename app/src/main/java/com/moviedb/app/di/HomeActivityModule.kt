package com.moviedb.app.di

import com.moviedb.app.HomeActivity
import com.moviedb.discover.di.MovieBuildersModule
import com.moviedb.more.di.MoreBuilderModule
import com.moviedb.people.di.PeopleBuildersModule
import com.moviedb.tvshows.di.TVShowBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Suppress("unused")
@Module
abstract class HomeActivityModule {
    @ContributesAndroidInjector(modules = [MovieBuildersModule::class, TVShowBuildersModule::class, MoreBuilderModule::class, PeopleBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}
