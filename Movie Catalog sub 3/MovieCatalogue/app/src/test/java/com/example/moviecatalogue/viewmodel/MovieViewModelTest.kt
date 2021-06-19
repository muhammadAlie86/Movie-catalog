package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.repositories.Repository
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<List<Movie>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovie() {

        val dummyMovies: Resource<List<Movie>> = Resource.success(DataDummy.generateDataDummyCatalogueMovie())

        val listMovie: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()
        listMovie.run {
            setValue(dummyMovies)
        }

        `when`(viewModel.getMovie()).thenReturn(listMovie)

        val movieEntities = viewModel.getMovie().value?.data
        verify(repository).getAllMovies()

        assertNotNull(movieEntities)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)

    }
}