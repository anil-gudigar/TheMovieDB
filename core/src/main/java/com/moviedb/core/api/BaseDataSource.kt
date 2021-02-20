package com.moviedb.core.api

import retrofit2.Response
import com.moviedb.core.data.DataWrapper
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

/**
 * Created by Anil Gudigar on 20,February,2021
 */
abstract class BaseDataSource {

    protected suspend fun <T> execute(call: suspend () -> Response<T>): DataWrapper<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return DataWrapper.success(body)
            }

            response.errorBody()?.let {
                //Log Event with category apierror
                when (response.code()) {
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        return error(response.code(), "Access Denied")
                    }
                    HttpsURLConnection.HTTP_BAD_REQUEST -> {
                        try {
                            val errorResponse = it.string()
                            val data = JSONObject(errorResponse)
                            var message = "Something went Wrong"
                            if (data.has("message")) message =
                                java.lang.String.valueOf(data.get("message"))
                            return error(response.code(), message)
                        } catch (e: IOException) {
                            return error(response.code(), "Something went Wrong")
                        }
                    }
                    HttpsURLConnection.HTTP_CONFLICT -> {
                        try {
                            val errorResponse = it.string()
                            val data = JSONObject(errorResponse)
                            var message = "Something went Wrong"
                            if (data.has("message")) message =
                                java.lang.String.valueOf(data.get("message"))
                            return error(response.code(), message)
                        } catch (e: IOException) {
                            return error(response.code(), "Something went Wrong")
                        }
                    }
                    HttpURLConnection.HTTP_FORBIDDEN -> {
                        try {
                            val errorResponse = it.string()
                            val data = JSONObject(errorResponse)
                            var message = "Something went Wrong"
                            if (data.has("message")) message =
                                java.lang.String.valueOf(data.get("message"))
                            return error(response.code(), message)
                        } catch (e: IOException) {
                            return error(response.code(), "Something went Wrong")
                        }
                    }
                    HttpsURLConnection.HTTP_NOT_FOUND -> {
                        try {
                            val errorResponse = it.string()
                            val data = JSONObject(errorResponse)
                            var message = "Something went Wrong"
                            if (data.has("message")) message =
                                java.lang.String.valueOf(data.get("message"))
                            return error(response.code(), message)
                        } catch (e: IOException) {
                            return error(response.code(), "Something went Wrong")
                        }
                    }
                    else -> {

                    }
                }
            }
            return error(response.code(), response.message())
        } catch (e: HttpException) {
            return error(HttpURLConnection.HTTP_SERVER_ERROR, e.message ?: e.toString())
        } catch (e: UnknownHostException) {
            return error(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, e.message ?: e.toString())
        } catch (e: IOException) {
            return error(HttpURLConnection.HTTP_SERVER_ERROR, e.message ?: e.toString())
        } catch (e: Exception) {
            return error(HttpURLConnection.HTTP_SERVER_ERROR, "Something went Wrong")
        }
    }

    private fun <T> error(statusCode: Int, message: String): DataWrapper<T> {
        return DataWrapper.error(statusCode, message)
    }
}