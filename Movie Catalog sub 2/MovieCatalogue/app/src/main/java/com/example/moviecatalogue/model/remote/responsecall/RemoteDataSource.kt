package com.example.moviecatalogue.model.remote.responsecall

import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.model.remote.entity.TvShow

class RemoteDataSource(private val callNetwork: CallNetwork) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(callNetwork: CallNetwork): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(callNetwork)
            }
    }

    fun getMovie(): MutableLiveData<List<Movie>> = callNetwork.getMovie()
    fun getTvShow(): MutableLiveData<List<TvShow>> = callNetwork.getTvShow()
    fun getMovieById(movieId: Int): MutableLiveData<Movie> = callNetwork.getMovieById(movieId)
    fun getTvById(tvId: Int): MutableLiveData<TvShow> = callNetwork.getTvById(tvId)
}