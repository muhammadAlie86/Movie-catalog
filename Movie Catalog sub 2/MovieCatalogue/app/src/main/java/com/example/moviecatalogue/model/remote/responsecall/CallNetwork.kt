package com.example.moviecatalogue.model.remote.responsecall

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.api.Constant.Companion.LANGUAGE
import com.example.moviecatalogue.api.Constant.Companion.PAGE
import com.example.moviecatalogue.api.RetrofitInstance
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.model.remote.entity.TvShow
import com.example.moviecatalogue.model.remote.responseentity.ResponseMovie
import com.example.moviecatalogue.model.remote.responseentity.ResponseTv
import com.example.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallNetwork(private val context: Context) {

    var listMovie: MutableLiveData<List<Movie>> = MutableLiveData()
    val listTv: MutableLiveData<List<TvShow>> = MutableLiveData()
    var listMovieById: MutableLiveData<Movie> = MutableLiveData()
    val listTvById: MutableLiveData<TvShow> = MutableLiveData()


    fun getMovie(): MutableLiveData<List<Movie>> {
        EspressoIdlingResource.increment()
        val service = RetrofitInstance.apiClient.getMovie(BuildConfig.API_KEY, LANGUAGE, PAGE)

        service.enqueue(object : Callback<ResponseMovie> {

            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                if (response.body() != null) {
                    listMovie.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {

                Toast.makeText(context, "Something Wrong ", Toast.LENGTH_SHORT).show()
            }

        })
        EspressoIdlingResource.decrement()

        return listMovie
    }

    fun getTvShow(): MutableLiveData<List<TvShow>> {

        EspressoIdlingResource.increment()
        val service = RetrofitInstance.apiClient.getTvShow(BuildConfig.API_KEY, LANGUAGE, PAGE)

        service.enqueue(object : Callback<ResponseTv> {
            override fun onResponse(call: Call<ResponseTv>, response: Response<ResponseTv>) {
                if (response.body() != null) {
                    listTv.postValue(response.body()?.result)

                }
            }

            override fun onFailure(call: Call<ResponseTv>, t: Throwable) {
                Toast.makeText(context, "Something Wrong ", Toast.LENGTH_SHORT).show()

            }

        })
        EspressoIdlingResource.decrement()
        return listTv

    }

    fun getMovieById(id: Int): MutableLiveData<Movie> {

        EspressoIdlingResource.increment()
        val service = RetrofitInstance.apiClient.getMovieById(id, BuildConfig.API_KEY, LANGUAGE)

        service.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    listMovieById.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Toast.makeText(context, "Something Wrong ", Toast.LENGTH_SHORT).show()

            }

        })
        EspressoIdlingResource.decrement()
        return listMovieById
    }

    fun getTvById(id: Int): MutableLiveData<TvShow> {

        EspressoIdlingResource.increment()
        val service = RetrofitInstance.apiClient.getTvById(id, BuildConfig.API_KEY, LANGUAGE)
        service.enqueue(object : Callback<TvShow> {
            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                if (response.isSuccessful) {
                    listTvById.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<TvShow>, t: Throwable) {

                Toast.makeText(context, "Something Wrong ", Toast.LENGTH_SHORT).show()

            }

        })
        EspressoIdlingResource.decrement()
        return listTvById

    }
}