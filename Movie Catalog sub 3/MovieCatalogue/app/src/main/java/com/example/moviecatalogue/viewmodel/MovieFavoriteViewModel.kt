package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.repositories.Repository

class MovieFavoriteViewModel(private val repository: Repository) : ViewModel(){

    fun getFavoriteMovie() : LiveData<PagedList<Movie>> =
        repository.getFavoriteMovie()

    fun setFavoriteMovie(movie: Movie) {
        val state = !movie.isFavorite
        repository.setFavoriteMovie(movie,state)
    }

}