package com.example.moviecatalogue.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.api.Constant.Companion.LANGUAGE
import com.example.moviecatalogue.api.Constant.Companion.PAGE
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.repositories.Repository
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.viewmodel.MovieViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovie() {

        val dummyMovies: List<Movie> = DataDummy.generateDataDummyCatalogueMovie()

        val listMovie: MutableLiveData<List<Movie>> = MutableLiveData()
        listMovie.run {
            setValue(dummyMovies)
        }

        Mockito.`when`(viewModel.getMovie(BuildConfig.API_KEY, LANGUAGE, PAGE)).thenReturn(listMovie)

        val movieEntities = viewModel.getMovie(BuildConfig.API_KEY, LANGUAGE, PAGE).value
        Mockito.verify(repository).getMovie(BuildConfig.API_KEY, LANGUAGE, PAGE)

        assertNotNull(movieEntities)
        assertEquals(5, dummyMovies.size)

        viewModel.getMovie(BuildConfig.API_KEY, LANGUAGE, PAGE).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)

    }
}