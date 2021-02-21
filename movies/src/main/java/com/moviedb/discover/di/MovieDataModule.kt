package com.moviedb.discover.di

import android.app.Application
import com.moviedb.core.di.CoreDataModule
import com.moviedb.discover.data.DiscoverRemoteDataSource
import com.moviedb.discover.data.DiscoverRepository
import com.moviedb.discover.data.db.MovieDatabase
import com.moviedb.discover.data.local.DetailsDao
import com.moviedb.discover.data.local.DiscoverDao
import com.moviedb.discover.data.remote.DiscoverService
import com.moviedb.discover.usecases.DiscoverMovieUsecase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module(includes = [CoreDataModule::class])
class MovieDataModule {

    @Singleton
    @Provides
    fun provideDb(app: Application) = MovieDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideDiscoverDao(db: MovieDatabase): DiscoverDao {
        return db.discoverDao()
    }

    @Singleton
    @Provides
    fun provideDetailsDao(db: MovieDatabase): DetailsDao {
        return db.detailsDao()
    }

    @Singleton
    @Provides
    fun provideDiscoverMovieUsecase(repository: DiscoverRepository): DiscoverMovieUsecase {
        return DiscoverMovieUsecase(repository)
    }

    @Singleton
    @Provides
    fun provideDiscoverRepository(
        dao: DiscoverDao,
        remoteDataSource: DiscoverRemoteDataSource
    ): DiscoverRepository {
        return DiscoverRepository(dao, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideDiscoverRemoteDataSource(service: DiscoverService): DiscoverRemoteDataSource {
        return DiscoverRemoteDataSource(service)
    }

    @Provides
    fun provideDiscoverService(retrofit: Retrofit): DiscoverService =
        retrofit.create(DiscoverService::class.java)

}