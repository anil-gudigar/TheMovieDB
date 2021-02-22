package com.moviedb.people.di

import android.app.Application
import com.moviedb.core.di.CoreDataModule
import com.moviedb.people.data.DiscoverPeopleRemoteDataSource
import com.moviedb.people.data.PeopleDetailRepository
import com.moviedb.people.data.PeopleDetialRemoteDataSource
import com.moviedb.people.data.PeopleDiscoverRepository
import com.moviedb.people.data.db.PeopleDatabase
import com.moviedb.people.data.local.PeopleDao
import com.moviedb.people.data.local.PeopleDetailsDao
import com.moviedb.people.data.remote.PeopleService
import com.moviedb.people.usecases.DiscoverPeopleUsecase
import com.moviedb.people.usecases.PeopleDetailsUsecase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module(includes = [CoreDataModule::class])
class PeopleDataModule {

    @Singleton
    @Provides
    fun provideDb(app: Application) = PeopleDatabase.getInstance(app)

    @Singleton
    @Provides
    fun providePeopleDao(db: PeopleDatabase): PeopleDao {
        return db.peopleDao()
    }

    @Singleton
    @Provides
    fun providePeopleDetailsDao(db: PeopleDatabase): PeopleDetailsDao {
        return db.peopleDetailsDao()
    }

    @Singleton
    @Provides
    fun provideDiscoverPeopleUsecase(repository: PeopleDiscoverRepository): DiscoverPeopleUsecase {
        return DiscoverPeopleUsecase(repository)
    }

    @Singleton
    @Provides
    fun providePeopleDiscoverRepository(
            dao: PeopleDao,
            remoteDataSource: DiscoverPeopleRemoteDataSource
    ): PeopleDiscoverRepository {
        return PeopleDiscoverRepository(dao, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideDiscoverRemoteDataSource(service: PeopleService): DiscoverPeopleRemoteDataSource {
        return DiscoverPeopleRemoteDataSource(service)
    }


    @Singleton
    @Provides
    fun providePeopleDetailsUsecase(repository: PeopleDetailRepository): PeopleDetailsUsecase {
        return PeopleDetailsUsecase(repository)
    }

    @Singleton
    @Provides
    fun provideDetailsRepository(
            dao: PeopleDetailsDao,
            remoteDataSource: PeopleDetialRemoteDataSource
    ): PeopleDetailRepository {
        return PeopleDetailRepository(dao, remoteDataSource)
    }

    @Singleton
    @Provides
    fun providePeopleDetialRemoteDataSource(service: PeopleService): PeopleDetialRemoteDataSource {
        return PeopleDetialRemoteDataSource(service)
    }

    @Provides
    fun providePeopleService(retrofit: Retrofit): PeopleService =
            retrofit.create(PeopleService::class.java)

}