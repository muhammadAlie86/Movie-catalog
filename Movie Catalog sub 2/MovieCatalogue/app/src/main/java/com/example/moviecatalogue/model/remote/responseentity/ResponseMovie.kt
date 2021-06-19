package com.example.moviecatalogue.model.remote.responseentity

import com.example.moviecatalogue.model.remote.entity.Movie


import com.google.gson.annotations.SerializedName

data class ResponseMovie(

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val result: List<Movie>
)