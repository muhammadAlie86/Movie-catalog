package com.example.moviecatalogue.model.remote.responsecall

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.model.remote.entity.TvShow

interface DataSource {

    fun getMovie(apiKey: String, language: String, page: Int): LiveData<List<Movie>>
    fun getTvShow(apiKey: String, language: String, page: Int): LiveData<List<TvShow>>
    fun getMovieById(id: Int): LiveData<Movie>
    fun getTvById(id: Int): LiveData<TvShow>
}