package com.moviedb.people.domian.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("adult")
    var adult: Boolean?,
    @SerializedName("gender")
    var gender: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("known_for")
    var knownFor: List<KnownFor>?,
    @SerializedName("known_for_department")
    var knownForDepartment: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("profile_path")
    var profilePath: String?
)