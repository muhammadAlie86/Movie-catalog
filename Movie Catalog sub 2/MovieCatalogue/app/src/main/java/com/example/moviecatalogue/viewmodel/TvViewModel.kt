package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.remote.entity.TvShow
import com.example.moviecatalogue.repositories.Repository

class TvViewModel(private val repository: Repository) : ViewModel() {

    fun getTvShow(apiKey: String, language: String, page: Int): LiveData<List<TvShow>> =
        repository.getTvShow(apiKey, language, page)

}