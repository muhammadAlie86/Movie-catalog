package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.CatalogueEntity
import com.example.moviecatalogue.utils.DataDummy

class DetailViewModel : ViewModel() {


    private lateinit var movieId : String
    private lateinit var tvShowId : String

    fun setMovie(movieId : String){
        this.movieId = movieId
    }
    fun setTvShow(tvShowId : String){
        this.tvShowId = tvShowId
    }
    private fun getItemsMovie () : ArrayList<CatalogueEntity> = DataDummy.generateDataDummyCatalogueMovie() as ArrayList<CatalogueEntity>

    fun getSelectedItem() : CatalogueEntity{
        lateinit var listMovie : CatalogueEntity
        val listItemMovie = getItemsMovie()
        for (item in listItemMovie){
            if (item.id == movieId){
                listMovie = item
                break
            }
        }
        return listMovie
    }
    private fun getItemsTv () : ArrayList<CatalogueEntity> = DataDummy.generateDataDummyCatalogueTv() as ArrayList<CatalogueEntity>

    fun getSelectedItemTv() : CatalogueEntity{
        lateinit var listTv : CatalogueEntity
        val listItemTv = getItemsTv()
        for (item in listItemTv){
            if (item.id == tvShowId){
                listTv = item
                break
            }
        }
        return listTv
    }
}