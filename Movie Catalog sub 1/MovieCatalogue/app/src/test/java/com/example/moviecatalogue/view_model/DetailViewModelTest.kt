package com.example.moviecatalogue.view_model

import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.viewmodel.DetailViewModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dataDummyMovie = DataDummy.generateDataDummyCatalogueMovie()[0]
    private val dataDummyTv = DataDummy.generateDataDummyCatalogueTv()[0]
    private val movieId = dataDummyMovie.id
    private val tvShowId = dataDummyTv.id


    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setMovie(movieId)
        viewModel.setTvShow(tvShowId)
    }

    @Test
    fun getSelectedItem() {

        val movie = viewModel.getSelectedItem()

        assertNotNull(movie)
        assertEquals(dataDummyMovie.id,movie.id)
        assertEquals(dataDummyMovie.title,movie.title)
        assertEquals(dataDummyMovie.date,movie.date)
        assertEquals(dataDummyMovie.duration,movie.duration)
        assertEquals(dataDummyMovie.genre,movie.genre)
        assertEquals(dataDummyMovie.description,movie.description)
        assertEquals(dataDummyMovie.director,movie.director)


    }

    @Test
    fun getSelectedItemTv() {

        val tvShow = viewModel.getSelectedItemTv()

        assertNotNull(tvShow)
        assertEquals(dataDummyTv.id,tvShow.id)
        assertEquals(dataDummyTv.title,tvShow.title)
        assertEquals(dataDummyTv.date,tvShow.date)
        assertEquals(dataDummyTv.duration,tvShow.duration)
        assertEquals(dataDummyTv.genre,tvShow.genre)
        assertEquals(dataDummyTv.description,tvShow.description)
        assertEquals(dataDummyTv.director,tvShow.director)
    }
}