package com.moviedb.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

/**
 * Created by Anil Gudigar on 20,February,2021
 */
/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [Result.Status.SUCCESS] - with data from database
 * [Result.Status.ERROR] - if error has occurred from any source
 * [Result.Status.LOADING]
 */
fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> DataWrapper<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<DataWrapper<T>> =
    liveData(Dispatchers.IO) {
        emit(DataWrapper.loading<T>())
        val source = databaseQuery.invoke().map { DataWrapper.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == DataWrapper.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == DataWrapper.Status.ERROR) {
            emit(DataWrapper.error<T>(responseStatus.message!!))
            emitSource(source)
        }
    }