package com.moviedb.people.di

import com.moviedb.people.view.PeopleDetailsFragment
import com.moviedb.people.view.PeopleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module
abstract class PeopleBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributePeopleFragment(): PeopleFragment

    @ContributesAndroidInjector
    abstract fun contributePeopleDetailsFragment(): PeopleDetailsFragment
}