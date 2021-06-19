package com.example.moviecatalogue.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.model.remote.entity.TvShow
import com.example.moviecatalogue.repositories.Repository
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.viewmodel.DetailViewModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel : DetailViewModel

    private val dummyMovie = DataDummy.generateDataDummyCatalogueMovie()[0]
    private val movieId = dummyMovie.id

    private val dummyTv = DataDummy.generateDataDummyCatalogueTv()[0]
    private val tvId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule =  InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<Movie>

    @Mock
    private lateinit var tvObserver: Observer<TvShow>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getMovieById() {
        val movieResult = MutableLiveData<Movie> ()
        movieResult.run {
            setValue(dummyMovie)
        }
        Mockito.`when`(movieId?.let { repository.getMovieById(it) }).thenReturn(movieResult)
        val listMovie = movieId?.let { viewModel.setMovie(it).value } as Movie

        assertNotNull(listMovie)
        assertEquals(dummyMovie.id,listMovie.id)
        assertEquals(dummyMovie.title,listMovie.title)
        assertEquals(dummyMovie.backdrop_img,listMovie.backdrop_img)
        assertEquals(dummyMovie.img_path,listMovie.img_path)
        assertEquals(dummyMovie.release_date,listMovie.release_date)
        assertEquals(dummyMovie.overview,listMovie.overview)
        assertEquals(dummyMovie.vote_average,listMovie.vote_average)

        viewModel.setMovie(movieId).observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyMovie)

    }


    @Test
    fun getTvById() {
        val tvResult = MutableLiveData<TvShow> ()
        tvResult.run {
            setValue(dummyTv)
        }
        Mockito.`when`(tvId?.let { repository.getTvById(it) }).thenReturn(tvResult)
        val listTv = tvId?.let { viewModel.setTvShow(it).value } as TvShow

        assertNotNull(listTv)
        assertEquals(dummyTv.id,listTv.id)
        assertEquals(dummyTv.title,listTv.title)
        assertEquals(dummyTv.backdrop_img,listTv.backdrop_img)
        assertEquals(dummyTv.imgPath,listTv.imgPath)
        assertEquals(dummyTv.release_date,listTv.release_date)
        assertEquals(dummyTv.overview,listTv.overview)
        assertEquals(dummyTv.vote_average,listTv.vote_average)

        viewModel.setTvShow(tvId).observeForever(tvObserver)
        Mockito.verify(tvObserver).onChanged(dummyTv)
    }
}