package com.moviedb.tvshows.di

import android.app.Application
import com.moviedb.core.di.CoreDataModule
import com.moviedb.tvshows.data.DetailsRemoteDataSource
import com.moviedb.tvshows.data.DetailsRepository
import com.moviedb.tvshows.data.DiscoverRemoteDataSource
import com.moviedb.tvshows.data.DiscoverRepository
import com.moviedb.tvshows.data.db.TVShowDatabase
import com.moviedb.tvshows.data.local.DetailsDao
import com.moviedb.tvshows.data.local.DiscoverDao
import com.moviedb.tvshows.data.remote.DiscoverService
import com.moviedb.tvshows.usecases.DiscoverTVShowsUsecase
import com.moviedb.tvshows.usecases.TVShowDetailsUsecase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Anil Gudigar on 21,February,2021
 */
@Module(includes = [CoreDataModule::class])
class TVShowDataModule {

    @Singleton
    @Provides
    fun provideDb(app: Application) = TVShowDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideDiscoverDao(db: TVShowDatabase): DiscoverDao {
        return db.discoverDao()
    }

    @Singleton
    @Provides
    fun provideDetailsDao(db: TVShowDatabase): DetailsDao {
        return db.detailsDao()
    }

    @Singleton
    @Provides
    fun provideDiscoverTVShowsUsecase(repository: DiscoverRepository): DiscoverTVShowsUsecase {
        return DiscoverTVShowsUsecase(repository)
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


    @Singleton
    @Provides
    fun provideTVShowDetailsUsecase(repository: DetailsRepository): TVShowDetailsUsecase {
        return TVShowDetailsUsecase(repository)
    }

    @Singleton
    @Provides
    fun provideDetailsRepository(
        dao: DetailsDao,
        remoteDataSource: DetailsRemoteDataSource
    ): DetailsRepository {
        return DetailsRepository(dao, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideDetailsRemoteDataSource(service: DiscoverService): DetailsRemoteDataSource {
        return DetailsRemoteDataSource(service)
    }

    @Provides
    fun provideDiscoverService(retrofit: Retrofit): DiscoverService =
        retrofit.create(DiscoverService::class.java)

}