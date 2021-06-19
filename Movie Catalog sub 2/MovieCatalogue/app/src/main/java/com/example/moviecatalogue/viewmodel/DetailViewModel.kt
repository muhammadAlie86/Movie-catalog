package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.model.remote.entity.TvShow
import com.example.moviecatalogue.repositories.Repository

class DetailViewModel(private val repository: Repository) : ViewModel() {


    fun setMovie(movieId: Int): LiveData<Movie> = repository.getMovieById(movieId)
    fun setTvShow(tvShowId: Int): LiveData<TvShow> = repository.getTvById(tvShowId)
}