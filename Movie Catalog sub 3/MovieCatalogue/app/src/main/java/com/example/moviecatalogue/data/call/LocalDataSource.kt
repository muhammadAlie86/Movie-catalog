package com.example.moviecatalogue.data.call

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.database.dao.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao){


    companion object {

        @Volatile
        private var instance: LocalDataSource? = null
        fun getInstance(movieDao: MovieDao)
        : LocalDataSource =
            instance ?: synchronized(this) {
                 LocalDataSource(movieDao).apply {
                     instance = this
                 }
            }
    }

    fun getAllMovies(): LiveData<List<Movie>> =
        movieDao.getAllMovies()

    fun getAllTvShows(): LiveData<List<TvShow>> =
        movieDao.getAllTvShows()

    fun getFavoriteMovies(): DataSource.Factory<Int,Movie> =
        movieDao.getFavoriteMovies()

    fun getFavoriteTvShows(): DataSource.Factory<Int,TvShow> =
        movieDao.getFavoriteTvShows()

    fun getMovieById(movieId: Int): LiveData<Movie> =
        movieDao.getMovieById(movieId)

    fun getTvShowById(tvId: Int): LiveData<TvShow> =
        movieDao.getTvShowById(tvId)

    fun insertMovie(movie: List<Movie>) =
        movieDao.insertMovie(movie)

    fun insertTvShow(tvShow: List<TvShow>) =
        movieDao.insertTvShow(tvShow)

    fun setFavoriteMovie(movie: Movie,state: Boolean) {
        movie.isFavorite = state
        movieDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShow,state : Boolean) {
        tvShow.isFavorite = state
        movieDao.updateTvShow(tvShow)
    }
}