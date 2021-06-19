package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.repositories.Repository
import com.example.moviecatalogue.vo.Resource

class DetailViewModel(private val repository: Repository): ViewModel() {

    private val movieId = MutableLiveData<Int>()
    private val tvShowId = MutableLiveData<Int>()


    fun setMovie(movieId: Int){
        this.movieId.value = movieId
    }
    fun setTvShow(tvShowId : Int){
        this.tvShowId.value = tvShowId
    }

    fun getMovieById(movieId: Int) : LiveData<Resource<Movie>> = repository.getMovieById(movieId)

    fun tvShowById(tvShowId: Int) : LiveData<Resource<TvShow>> = repository.getTvById(tvShowId)

    var favoriteMovie : LiveData<Resource<Movie>> = Transformations.switchMap(movieId){ mMovieId ->
        repository.getMovieById(mMovieId)
    }
    var favoriteTv : LiveData<Resource<TvShow>> = Transformations.switchMap(tvShowId){ mTvShowId ->
        repository.getTvById(mTvShowId)
    }

    fun setFavoriteMovie(){

        val favMovie = favoriteMovie.value

        if (favMovie != null) {
            val favoriteMovieEntity = favMovie.data

            if (favoriteMovieEntity != null) {
                val newState = !favoriteMovieEntity.isFavorite
                repository.setFavoriteMovie(favoriteMovieEntity, newState)
            }
        }
    }
    fun setFavoriteTvShow(){

        val favTvShow = favoriteTv.value

        if (favTvShow != null){
            val favoriteTvShowEntity = favTvShow.data

            if (favoriteTvShowEntity != null){
                val newState = !favoriteTvShowEntity.isFavorite
                repository.setFavoriteTvShow(favoriteTvShowEntity,newState)
            }
        }
    }


}
