package com.moviedb.people.data.remote

import com.moviedb.people.domian.model.DiscoverPeople
import com.moviedb.people.domian.model.People
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Anil Gudigar on 22,February,2021
 */
interface PeopleService {
    @GET("person/popular")
    suspend fun getPersonList(
            @Query("api_key") api_key: String? = null,
            @Query("language") language: String? = null,
            @Query("page") page: Int? = null
    ): Response<DiscoverPeople>


    @GET("person/{person_id}")
    suspend fun getPerson(
            @Path("person_id") person_id: String? = null,
            @Query("api_key") api_key: String? = null,
            @Query("language") language: String? = null
    ): Response<People>
}