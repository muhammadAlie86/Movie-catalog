package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.call.CallNetwork
import com.example.moviecatalogue.data.call.LocalDataSource
import com.example.moviecatalogue.data.call.RemoteDataSource
import com.example.moviecatalogue.database.room.MovieDatabase
import com.example.moviecatalogue.repositories.Repository

object Injection {

    fun provideRepository(context: Context): Repository {
        val database = MovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(CallNetwork(context))
        val localDataSource = LocalDataSource.getInstance(database.movieDao())


        return Repository.getInstance(remoteDataSource,localDataSource)
    }
}