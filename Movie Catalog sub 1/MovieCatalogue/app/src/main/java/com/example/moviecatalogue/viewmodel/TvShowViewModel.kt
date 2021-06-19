package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.CatalogueEntity
import com.example.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getTvShow() : List<CatalogueEntity> = DataDummy.generateDataDummyCatalogueTv()


}