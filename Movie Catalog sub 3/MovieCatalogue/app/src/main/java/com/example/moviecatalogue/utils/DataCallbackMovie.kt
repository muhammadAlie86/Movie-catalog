package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.local.entity.Movie

interface DataCallbackMovie {

    fun setDataMovie(data : Movie)
}