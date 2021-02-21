package com.moviedb.more.di

import com.moviedb.more.view.MoreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anil Gudigar on 22,February,2021
 */
@Module
abstract class MoreBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMoreFragment(): MoreFragment
}