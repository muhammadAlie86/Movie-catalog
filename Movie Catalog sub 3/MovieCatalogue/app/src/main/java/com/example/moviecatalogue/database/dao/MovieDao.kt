package com.example.moviecatalogue.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_entities ORDER BY movieId ASC")
    fun getAllMovies() : LiveData<List<Movie>>

    @Query("SELECT * FROM tvShow_entities ORDER BY tvId ASC")
    fun getAllTvShows() : LiveData<List<TvShow>>

    @Query("SELECT * FROM movie_entities WHERE movieId = :movieId" )
    fun getMovieById(movieId : Int) : LiveData<Movie>

    @Query("SELECT * FROM tvShow_entities WHERE tvId = :tvId" )
    fun getTvShowById(tvId : Int): LiveData<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie (movie : List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow (tvShow : List<TvShow>)

    @Update
    fun updateMovie(movie: Movie)

    @Update
    fun updateTvShow(tvShow: TvShow)

    @Query("SELECT * FROM movie_entities WHERE favorite = 1")
    fun getFavoriteMovies() : DataSource.Factory<Int,Movie>

    @Query("SELECT * FROM tvShow_entities WHERE favorite = 1")
    fun getFavoriteTvShows() : DataSource.Factory<Int,TvShow>

}