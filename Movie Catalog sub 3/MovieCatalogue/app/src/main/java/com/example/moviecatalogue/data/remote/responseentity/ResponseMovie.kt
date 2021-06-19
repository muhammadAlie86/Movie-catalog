@file:Suppress("unused")

package com.example.moviecatalogue.data.remote.responseentity

import com.example.moviecatalogue.data.local.entity.Movie


import com.google.gson.annotations.SerializedName

data class ResponseMovie <T>(

    @SerializedName("results")
    val result : List<Movie>
)