package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.model.remote.responsecall.CallNetwork
import com.example.moviecatalogue.model.remote.responsecall.RemoteDataSource
import com.example.moviecatalogue.repositories.Repository

object Injection {

    fun provideRepository(context: Context): Repository {

        val remoteDataSource = RemoteDataSource.getInstance(CallNetwork(context))

        return Repository.getInstance(remoteDataSource)
    }
}