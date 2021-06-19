package com.example.moviecatalogue.api

import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.api.Constant.Companion.LANGUAGE
import com.example.moviecatalogue.api.Constant.Companion.PAGE
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.data.remote.responseentity.ResponseMovie
import com.example.moviecatalogue.data.remote.responseentity.ResponseTv
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("movie/now_playing?")
    fun getMovie (
        @Query ("api_key") api_key : String = BuildConfig.API_KEY,
        @Query("language") language : String = LANGUAGE,
        @Query("page") page : Int = PAGE
    ): Call<ResponseMovie<Movie>>

    @GET("tv/popular?")
    fun getTvShow(
        @Query ("api_key") api_key : String = BuildConfig.API_KEY,
        @Query("language") language : String = LANGUAGE,
        @Query("page") page : Int = PAGE
    ): Call<ResponseTv<TvShow>>

    @GET("movie/{movie_id}")
    fun getMovieById(
        @Path("movie_id") id : Int,
        @Query ("api_key") api_key : String = BuildConfig.API_KEY,
        @Query("language") language : String = LANGUAGE
    ): Call<Movie>

    @GET("tv/{tv_id}")
    fun getTvById(
        @Path("tv_id") id : Int,
        @Query ("api_key") api_key : String = BuildConfig.API_KEY,
        @Query("language") language : String = LANGUAGE
    ): Call<TvShow>
}