package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.repositories.Repository
import com.example.moviecatalogue.vo.Resource

class TvViewModel(private val repository: Repository) : ViewModel() {

    fun getTvShow() : LiveData<Resource<List<TvShow>>> =
        repository.getAllTvShows()

}