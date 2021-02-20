package com.moviedb.core.data

/**
 * Created by Anil Gudigar on 20,February,2021
 */
/**
 * A generic class that holds a value with its loading status.
 *
 * Result is usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */

class DataWrapper<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val statusCode: Int = 0
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): DataWrapper<T> {
            return DataWrapper(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(message: String, data: T? = null): DataWrapper<T> {
            return DataWrapper(
                Status.ERROR,
                data,
                message
            )
        }

        fun <T> error(statusCode: Int, message: String, data: T? = null): DataWrapper<T> {
            return DataWrapper(
                Status.ERROR,
                data,
                message,
                statusCode
            )
        }

        fun <T> loading(data: T? = null): DataWrapper<T> {
            return DataWrapper(
                Status.LOADING,
                data,
                null
            )
        }
    }

    override fun toString(): String {
        return "DataWrapper(status=$status, data=$data, message=$message, statusCode=$statusCode)"
    }

}
