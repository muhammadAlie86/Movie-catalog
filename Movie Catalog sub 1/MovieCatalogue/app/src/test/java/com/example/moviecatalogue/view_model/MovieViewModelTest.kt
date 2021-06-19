package com.example.moviecatalogue.view_model

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.CatalogueEntity
import com.example.moviecatalogue.view.fragment.MovieFragment
import com.example.moviecatalogue.viewmodel.MovieViewModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel


    @Before
    fun setUp() {
        movieViewModel = MovieViewModel()
    }

    @Test
    fun getMovie() {

        val catalogueEntity = movieViewModel.getMovie()
        assertNotNull(catalogueEntity)
        assertEquals(10,catalogueEntity.size)

    }
}