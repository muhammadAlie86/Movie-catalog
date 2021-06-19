package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.repositories.Repository
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.PagedList.mockPagedList
import com.example.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MovieFavoriteViewModelTest{

    private lateinit var viewModel: MovieFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var pagedList: PagedList<Movie>

    @Mock
    private lateinit var observer: Observer<PagedList<Movie>>

    @Before
    fun setUp() {
        viewModel = MovieFavoriteViewModel(repository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<Movie>>()
        movie.value = dummyMovie
        `when`(repository.getFavoriteMovie()).thenReturn(movie)
        repository.getFavoriteMovie()

        val tvShowEntities = Resource.success(
            mockPagedList(
                DataDummy.generateDataDummyCatalogueMovie()
            )
        )
        verify(repository).getFavoriteMovie()
        assertNotNull(tvShowEntities.data)
        assertEquals(5, tvShowEntities.data?.size)

        viewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
    @Test
    fun setFavoriteMovie() {
        val dataDummy = DataDummy.generateDataDummyCatalogueMovie()[0].copy(isFavorite = false)
        doNothing().`when`(repository).setFavoriteMovie(dataDummy,true)
        repository.setFavoriteMovie(dataDummy,true)
        verify(repository, times(1)).setFavoriteMovie(dataDummy,true)
    }



}