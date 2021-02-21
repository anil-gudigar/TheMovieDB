package com.moviedb.app.di

import com.moviedb.core.di.CoreDataModule
import com.moviedb.discover.di.MovieDataModule
import dagger.Module

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module(
    includes = [ViewModelModule::class, CoreDataModule::class, MovieDataModule::class]
)
class AppModule