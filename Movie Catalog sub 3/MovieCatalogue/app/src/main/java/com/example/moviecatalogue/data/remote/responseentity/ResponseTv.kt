package com.example.moviecatalogue.data.remote.responseentity

import com.example.moviecatalogue.data.local.entity.TvShow
import com.google.gson.annotations.SerializedName

data class ResponseTv <T>(

    @SerializedName("results")
    val result : List<TvShow>
)