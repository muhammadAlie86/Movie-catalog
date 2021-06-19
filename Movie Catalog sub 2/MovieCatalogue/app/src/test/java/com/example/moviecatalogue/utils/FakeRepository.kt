package com.example.moviecatalogue.utils


import androidx.lifecycle.LiveData
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.model.remote.entity.TvShow
import com.example.moviecatalogue.model.remote.responsecall.RemoteDataSource

class FakeRepository(private val remoteDataSource: RemoteDataSource) {

    fun getMovie(): LiveData<List<Movie>> {
        return remoteDataSource.getMovie()
    }

    fun getTvShow(): LiveData<List<TvShow>> {
        return remoteDataSource.getTvShow()
    }

    fun getMovieById(id: Int): LiveData<Movie> {
        return remoteDataSource.getMovieById(id)
    }

    fun getTvById(id: Int): LiveData<TvShow> {
        return remoteDataSource.getTvById(id)
    }
}