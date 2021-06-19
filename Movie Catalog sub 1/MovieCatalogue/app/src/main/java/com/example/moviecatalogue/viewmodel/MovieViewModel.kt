package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.CatalogueEntity
import com.example.moviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun getMovie() : List<CatalogueEntity> = DataDummy.generateDataDummyCatalogueMovie()

}