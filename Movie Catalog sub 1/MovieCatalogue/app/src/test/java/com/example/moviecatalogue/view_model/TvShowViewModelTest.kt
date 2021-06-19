package com.example.moviecatalogue.view_model

import com.example.moviecatalogue.viewmodel.TvShowViewModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {

        val catalogueEntity = viewModel.getTvShow()
        assertNotNull(catalogueEntity)
        assertEquals(10,catalogueEntity.size)
    }
}