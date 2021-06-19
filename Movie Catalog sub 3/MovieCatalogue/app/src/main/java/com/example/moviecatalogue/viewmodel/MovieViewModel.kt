package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.repositories.Repository
import com.example.moviecatalogue.vo.Resource


class MovieViewModel (private val repository: Repository): ViewModel() {

    fun getMovie() : LiveData<Resource<List<Movie>>> =
        repository.getAllMovies()

}