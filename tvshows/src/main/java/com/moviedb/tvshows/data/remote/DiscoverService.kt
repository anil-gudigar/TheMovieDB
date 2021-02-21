package com.moviedb.tvshows.data.remote

import com.moviedb.common.domain.model.Movie
import com.moviedb.discover.domain.model.DiscoverMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DiscoverService {

    @GET("discover/tv")
    suspend fun getMoviesList(
        @Query("api_key") api_key: String? = null,
        @Query("language") language: String? = null,
        @Query("sort_by") sort_by: String? = null,
        @Query("include_adult") include_adult: String? = null,
        @Query("include_video") include_video: String? = null,
        @Query("page") page: Int? = null
    ): Response<DiscoverMovie>


    @GET("tv/{tv_id}")
    suspend fun getMovie(
        @Path("tv_id") tv_id: String? = null,
        @Query("api_key") api_key: String? = null,
        @Query("language") language: String? = null
    ): Response<Movie>

}