package com.moviedb.people.domian.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.moviedb.common.converter.Converters
import com.moviedb.core.BuildConfig

@Entity(tableName = "people")
@TypeConverters(Converters::class)
data class People(
        @SerializedName("adult")
        var adult: Boolean?,
        @SerializedName("also_known_as")
        var alsoKnownAs: List<String>?,
        @SerializedName("biography")
        var biography: String?,
        @SerializedName("birthday")
        var birthday: String?,
        @SerializedName("deathday")
        var deathday: String?,
        @SerializedName("homepage")
        var homepage: String?,
        @SerializedName("gender")
        var gender: Int?,
        @SerializedName("id")
        @PrimaryKey
        var id: Int?,
        @SerializedName("imdb_id")
        var imdbId: String?,
        @SerializedName("known_for")
        var knownFor: List<KnownFor>?,
        @SerializedName("known_for_department")
        var knownForDepartment: String?,
        @SerializedName("place_of_birth")
        var placeOfBirth: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("popularity")
        var popularity: Double?,
        @SerializedName("profile_path")
        var profilePath: String?
) {
    fun getCompleteImageUrl(): String {
        return BuildConfig.BASE_IMAGE_URL + profilePath
    }

    fun getPopularityRounded(): Int {
        return popularity?.toInt() ?: 0
    }

    fun getPopularityRoundedString(): String {
        return String.format("%.1f", popularity)
    }

    override fun toString(): String {
        return "People(adult=$adult, gender=$gender, id=$id, knownFor=$knownFor, knownForDepartment=$knownForDepartment, name=$name, popularity=$popularity, profilePath=$profilePath)"
    }
}