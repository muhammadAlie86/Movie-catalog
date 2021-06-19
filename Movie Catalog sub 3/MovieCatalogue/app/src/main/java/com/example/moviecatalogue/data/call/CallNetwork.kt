package com.example.moviecatalogue.data.call

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.api.RetrofitInstance
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.data.remote.responseentity.ResponseMovie
import com.example.moviecatalogue.data.remote.responsestatus.ApiResponse
import com.example.moviecatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await

class CallNetwork(context: Context) {

    fun getMovie(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val listMovie = MutableLiveData<ApiResponse<List<Movie>>>()
        CoroutineScope(IO).launch {
            try {
                val response = RetrofitInstance.apiClient.getMovie().await()
                listMovie.postValue(ApiResponse.success(response.result))

            } catch (e: Exception) {
                listMovie.postValue(ApiResponse.error(e.toString()))
            }
        }
        EspressoIdlingResource.decrement()

        return listMovie
    }

    fun getTvShow(): LiveData<ApiResponse<List<TvShow>>> {

        EspressoIdlingResource.increment()

        val listTv = MutableLiveData<ApiResponse<List<TvShow>>>()
        CoroutineScope(IO).launch {
            try {
                val response = RetrofitInstance.apiClient.getTvShow().await()
                listTv.postValue(ApiResponse.success(response.result))

            } catch (e: Exception) {
                listTv.postValue(ApiResponse.error(e.toString()))
            }
        }
        EspressoIdlingResource.decrement()
        return listTv

    }

    fun getMovieById(movieId: Int): LiveData<ApiResponse<Movie>> {

        EspressoIdlingResource.increment()

        val listMovieById = MutableLiveData<ApiResponse<Movie>>()

        CoroutineScope(IO).launch {
            try {
                val response = RetrofitInstance.apiClient.getMovieById(movieId).await()
                listMovieById.postValue(ApiResponse.success(response))

            } catch (e: Exception) {
                listMovieById.postValue(ApiResponse.error(e.toString()))
            }
        }
        EspressoIdlingResource.decrement()
        return listMovieById
    }

    fun getTvById(tvId: Int): LiveData<ApiResponse<TvShow>> {

        EspressoIdlingResource.increment()

        val listTvById = MutableLiveData<ApiResponse<TvShow>>()
        CoroutineScope(IO).launch {
            try {
                val response = RetrofitInstance.apiClient.getTvById(tvId).await()
                listTvById.postValue(ApiResponse.success(response))

            } catch (e: Exception) {
                listTvById.postValue(ApiResponse.error(e.toString()))
            }

            EspressoIdlingResource.decrement()


        }
        return listTvById
    }
}