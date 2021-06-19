package com.example.moviecatalogue.model.remote.responseentity

import com.example.moviecatalogue.model.remote.entity.TvShow
import com.google.gson.annotations.SerializedName

data class ResponseTv(

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val result: List<TvShow>
)