package com.moviedb.app.di

import com.moviedb.core.di.CoreDataModule
import dagger.Module

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module(
    includes = [ViewModelModule::class,
        CoreDataModule::class]
)
class AppModule {

}