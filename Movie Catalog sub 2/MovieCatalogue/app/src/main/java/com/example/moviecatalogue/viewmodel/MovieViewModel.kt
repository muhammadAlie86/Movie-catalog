package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.repositories.Repository


class MovieViewModel(private val repository: Repository) : ViewModel() {


    fun getMovie(apiKey: String, language: String, page: Int): LiveData<List<Movie>> =
        repository.getMovie(apiKey, language, page)

}