package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.repositories.Repository

class TvFavoriteViewModel (private val repository: Repository) : ViewModel() {

    fun getFavoriteTvShow() : LiveData<PagedList<TvShow>> = repository.getFavoriteTvShow()

    fun setFavoriteTvShow(tvShow: TvShow){
        val state = !tvShow.isFavorite
        repository.setFavoriteTvShow(tvShow,state)
    }
}