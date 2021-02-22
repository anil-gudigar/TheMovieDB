package com.moviedb.people.domian.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "knownfor")
data class KnownFor(
        @SerializedName("adult")
        var adult: Boolean?,
        @SerializedName("backdrop_path")
        var backdropPath: String?,
        @SerializedName("first_air_date")
        var firstAirDate: String?,
        @SerializedName("genre_ids")
        var genreIds: List<Int>?,
        @SerializedName("id")
        @PrimaryKey
        var id: Int?,
        @SerializedName("media_type")
        var mediaType: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("origin_country")
        var originCountry: List<String>?,
        @SerializedName("original_language")
        var originalLanguage: String?,
        @SerializedName("original_name")
        var originalName: String?,
        @SerializedName("original_title")
        var originalTitle: String?,
        @SerializedName("overview")
        var overview: String?,
        @SerializedName("poster_path")
        var posterPath: String?,
        @SerializedName("release_date")
        var releaseDate: String?,
        @SerializedName("title")
        var title: String?,
        @SerializedName("video")
        var video: Boolean?,
        @SerializedName("vote_average")
        var voteAverage: Double?,
        @SerializedName("vote_count")
        var voteCount: Int?
)