package com.moviedb.discover.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.moviedb.common.domain.model.Movie
import com.moviedb.core.BuildConfig
import com.moviedb.core.api.BaseDataSource
import com.moviedb.core.data.DataWrapper
import com.moviedb.discover.data.remote.DiscoverService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Works with the Discover to get data.
 */
class DiscoverRemoteDataSource @Inject constructor(private val service: DiscoverService) :
    PagingSource<Int, Movie>() {
    private val STARTING_PAGE_INDEX = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getMoviesList(
                BuildConfig.API_DEVELOPER_TOKEN,
                "en",
                "popularity.desc",
                "true",
                "true",
                position
            )
            var movies: List<Movie> = arrayListOf()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    movies = body.results ?: arrayListOf()
                }
            }
            Log.d("Anil", "Service -> getMovies: ${movies.size}")
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