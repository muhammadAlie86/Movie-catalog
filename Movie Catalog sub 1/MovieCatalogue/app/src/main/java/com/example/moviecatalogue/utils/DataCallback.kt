package com.example.moviecatalogue.utils

import com.example.moviecatalogue.model.CatalogueEntity

interface DataCallback {

    fun setData(data : CatalogueEntity)
}