package com.moviedb.people.data

import androidx.paging.PagingSource
import com.moviedb.core.BuildConfig
import com.moviedb.people.data.remote.PeopleService
import com.moviedb.people.domian.model.People
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Works with the Discover to get data.
 */
class DiscoverPeopleRemoteDataSource @Inject constructor(private val service: PeopleService) :
        PagingSource<Int, People>() {
    private val STARTING_PAGE_INDEX = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getPersonList(
                    BuildConfig.API_DEVELOPER_TOKEN,
                    "en",
                    position
            )
            var movies: List<People> = arrayListOf()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    movies = body.results ?: arrayListOf()
                }
            }
            LoadResult.Page(
                    data = movies,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position,
                    nextKey = if (movies.isEmpty()) null else position + 1

            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /*suspend fun fetchData(page:Int) = execute {
        service.getMoviesList(
            BuildConfig.API_DEVELOPER_TOKEN,
            "en",
            "popularity.desc",
            "true",
            "true",
            page
        )
    }*/

}