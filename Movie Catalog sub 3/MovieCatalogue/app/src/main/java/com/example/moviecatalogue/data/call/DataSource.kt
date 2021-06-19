package com.example.moviecatalogue.data.call

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.vo.Resource

interface DataSource {

    fun getAllMovies() : LiveData<Resource<List<Movie>>>
    fun getAllTvShows() : LiveData<Resource<List<TvShow>>>
    fun getMovieById(movieId : Int) : LiveData<Resource<Movie>>
    fun getTvById(tvId : Int) : LiveData<Resource<TvShow>>
    fun getFavoriteMovie() : LiveData<PagedList<Movie>>
    fun getFavoriteTvShow() : LiveData<PagedList<TvShow>>
    fun setFavoriteMovie (movie: Movie,state : Boolean)
    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)

}