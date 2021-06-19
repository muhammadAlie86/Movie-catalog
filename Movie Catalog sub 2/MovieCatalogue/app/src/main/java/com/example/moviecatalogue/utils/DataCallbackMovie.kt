package com.example.moviecatalogue.utils

import com.example.moviecatalogue.model.remote.entity.Movie

interface DataCallbackMovie {

    fun setDataMovie(data: Movie)
}