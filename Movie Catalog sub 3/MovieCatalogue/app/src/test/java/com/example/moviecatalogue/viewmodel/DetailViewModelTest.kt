package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.repositories.Repository
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel : DetailViewModel

    private val dummyMovie = Resource.success(DataDummy.generateDataDummyCatalogueMovie()[0])
    private val movieId = dummyMovie.data?.id

    private val dummyTv = Resource.success(DataDummy.generateDataDummyCatalogueTv()[0])
    private val tvId = dummyMovie.data?.id


    @get:Rule
    var instantTaskExecutorRule =  InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<Resource<Movie>>

    @Mock
    private lateinit var tvObserver: Observer<Resource<TvShow>>


    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getMovieById() {
        val movieResult = MutableLiveData<Resource<Movie>> ()
        movieResult.run {
            setValue(dummyMovie)
        }
        `when`(movieId?.let { repository.getMovieById(it) }).thenReturn(movieResult)


        movieId?.let { viewModel.getMovieById(it).observeForever(movieObserver) }
        verify(movieObserver).onChanged(dummyMovie)

    }
    @Test
    fun getTvById() {
        val tvResult = MutableLiveData<Resource<TvShow>> ()
        tvResult.run {
            setValue(dummyTv)
        }
        `when`(tvId?.let { repository.getTvById(it) }).thenReturn(tvResult)

        tvId?.let { viewModel.tvShowById(it).observeForever(tvObserver) }
        verify(tvObserver).onChanged(dummyTv)
    }
    @Test
    fun setFavoriteMovie() {
        val dataDummy = DataDummy.generateDataDummyCatalogueMovie()[0].copy(isFavorite = false)
        doNothing().`when`(repository).setFavoriteMovie(dataDummy,true)
        repository.setFavoriteMovie(dataDummy,true)
        verify(repository, times(1)).setFavoriteMovie(dataDummy,true)
    }
    @Test
    fun setFavoriteTv() {
        val dataDummy = DataDummy.generateDataDummyCatalogueTv()[0].copy(isFavorite = false)
        doNothing().`when`(repository).setFavoriteTvShow(dataDummy,true)
        repository.setFavoriteTvShow(dataDummy,true)
        verify(repository, times(1)).setFavoriteTvShow(dataDummy,true)
    }
}