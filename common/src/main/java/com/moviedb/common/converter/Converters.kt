package com.moviedb.common.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.moviedb.common.domain.model.Genres

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {

    @TypeConverter
    fun jsonGenresToList(value: String): List<Genres>? {
        var list = listOf<Genres>()
        if (!Gson().fromJson(value, Array<Genres>::class.java).isNullOrEmpty()) {
            val objects = Gson().fromJson(value, Array<Genres>::class.java) as Array<Genres>
            list = objects.toList()
        }
        return list
    }

    @TypeConverter
    fun listGenresToJson(value: List<Genres>?): String {
        return Gson().toJson(value)
    }
}