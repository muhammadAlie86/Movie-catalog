package com.example.moviecatalogue.data.call

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.data.remote.responsestatus.ApiResponse

class RemoteDataSource (private val callNetwork: CallNetwork) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(callNetwork: CallNetwork): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(callNetwork).apply {
                    instance = this
                }
            }
    }
    fun getMovie() : LiveData<ApiResponse<List<Movie>>> = callNetwork.getMovie()
    fun getTvShow() : LiveData<ApiResponse<List<TvShow>>> = callNetwork.getTvShow()
    fun getMovieById(movieId : Int) : LiveData<ApiResponse<Movie>> = callNetwork.getMovieById(movieId)
    fun getTvById(tvId: Int) : LiveData<ApiResponse<TvShow>> = callNetwork.getTvById(tvId)
}