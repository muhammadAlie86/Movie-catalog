package com.example.moviecatalogue.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.database.dao.MovieDao

@Database(entities = [Movie::class,TvShow::class],version = 1,exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object{
        @Volatile
        private var INSTANCE : MovieDatabase? = null

        fun getInstance(context: Context) : MovieDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                MovieDatabase::class.java,
                "Movie.db").build().apply {
                    INSTANCE = this
                }
            }

    }
}