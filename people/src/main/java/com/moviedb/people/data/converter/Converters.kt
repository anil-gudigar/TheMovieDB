package com.moviedb.people.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moviedb.people.domian.model.KnownFor
import java.lang.reflect.Type


/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {

    @TypeConverter
    fun jsonKnownForToList(value: String): List<KnownFor>? {
        var list = listOf<KnownFor>()
        if (!Gson().fromJson(value, Array<KnownFor>::class.java).isNullOrEmpty()) {
            val objects = Gson().fromJson(value, Array<KnownFor>::class.java) as Array<KnownFor>
            list = objects.toList()
        }
        return list
    }

    @TypeConverter
    fun listKnownForToJson(value: List<KnownFor>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }

    @TypeConverter
    fun gettingListFromString(genreIds: String): List<Int> {
        val list = mutableListOf<Int>()

        val array = genreIds.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()

        for (s in array) {
            if (s.isNotEmpty()) {
                list.add(s.toInt())
            }
        }
        return list
    }

    @TypeConverter
    fun writingStringFromList(list: List<Int>): String {
        var genreIds = ""
        for (i in list) genreIds += ",$i"
        return genreIds
    }
}