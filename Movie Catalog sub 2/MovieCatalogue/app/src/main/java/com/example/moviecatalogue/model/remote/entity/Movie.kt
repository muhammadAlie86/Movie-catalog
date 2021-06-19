package com.example.moviecatalogue.model.remote.entity


import com.google.gson.annotations.SerializedName


data class Movie(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("backdrop_path")
    val backdrop_img: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("poster_path")
    val img_path: String?,

    @SerializedName("original_language")
    val language: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val release_date: String?,

    @SerializedName("vote_average")
    val vote_average: String?
)
