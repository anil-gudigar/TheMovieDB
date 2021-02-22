package com.moviedb.discover.data.remote

import com.moviedb.common.domain.model.DiscoverMovie
import com.moviedb.common.domain.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DiscoverService {

    @GET("discover/movie")
    suspend fun getMoviesList(
            @Query("api_key") api_key: String? = null,
            @Query("language") language: String? = null,
            @Query("sort_by") sort_by: String? = null,
            @Query("include_adult") include_adult: String? = null,
            @Query("include_video") include_video: String? = null,
            @Query("page") page: Int? = null
    ): Response<DiscoverMovie>


    @GET("movie/{movieid}")
    suspend fun getMovie(
            @Path("movieid") movieid: String? = null,
            @Query("api_key") api_key: String? = null,
            @Query("language") language: String? = null
    ): Response<Movie>

}