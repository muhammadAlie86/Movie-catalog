package com.example.moviecatalogue.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.call.LocalDataSource
import com.example.moviecatalogue.data.call.RemoteDataSource
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.FakeRepository
import com.example.moviecatalogue.utils.LiveDataTestUtils
import com.example.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class RepositoryTest{

    private val listMovie = DataDummy.generateDataDummyCatalogueMovie()
    private var movieId = listMovie[0].id
    private val listTv = DataDummy.generateDataDummyCatalogueTv()
    private val tvId = listTv[0].id

    private val responseMovie = DataDummy.generateDataDummyCatalogueMovie()
    private val responseTv = DataDummy.generateDataDummyCatalogueTv()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val repository = FakeRepository(remote,local)
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getAllMovie() {
        val dummyMovie : MutableLiveData<List<Movie>> = MutableLiveData()
        dummyMovie.value = responseMovie
        `when`(local.getAllMovies()).thenReturn(dummyMovie)
        val result : Resource<List<Movie>> = LiveDataTestUtils.getValue(repository.getAllMovies())

        verify(local).getAllMovies()
        assertNotNull(result.data)
        assertEquals(responseMovie.size.toLong(), responseMovie.size.toLong())


    }

    @Test
    fun getTvShow() {
        val dummyTv : MutableLiveData<List<TvShow>> = MutableLiveData()
        dummyTv.value = responseTv
        `when`(local.getAllTvShows()).thenReturn(dummyTv)

        val result : Resource<List<TvShow>> = LiveDataTestUtils.getValue(repository.getAllTvShows())
        verify(local).getAllTvShows()
        assertNotNull(result.data)
        assertEquals(responseTv.size.toLong(), responseTv.size.toLong())


    }

    @Test
    fun getMovieById() {
        val dummyMovieById : MutableLiveData<Movie> = MutableLiveData()
        dummyMovieById.value = listMovie[0]
        `when`(movieId?.let { local.getMovieById(it) }).thenReturn(dummyMovieById)

        val result = LiveDataTestUtils.getValue(repository.getMovieById(movieId ?: 0))

        verify(local).getMovieById(movieId ?: 0)
        assertNotNull(result.data)
        assertEquals(listMovie.size.toLong(), listMovie.size.toLong())


    }

    @Test
    fun getTvById() {
        val dummyTvById : MutableLiveData<TvShow> = MutableLiveData()
        dummyTvById.value = listTv[0]

        `when`(tvId?.let { local.getTvShowById(it) }).thenReturn(dummyTvById)

        val result = LiveDataTestUtils.getValue(repository.getTvById(tvId ?: 0))

        verify(local).getTvShowById(tvId ?: 0)
        assertNotNull(result)
        assertEquals(listTv.size.toLong(), listTv.size.toLong())


    }
    @Test
    fun setFavoriteMovie() {
        val dataDummy = DataDummy.generateDataDummyCatalogueMovie()[0].copy(isFavorite = false)
        doNothing().`when`(local).setFavoriteMovie(dataDummy,true)
        repository.setFavoriteMovie(dataDummy,true)
        verify(local, times(1)).setFavoriteMovie(dataDummy,true)
    }

    @Test
    fun setFavoriteTv() {
        val dataDummy = DataDummy.generateDataDummyCatalogueTv()[0].copy(isFavorite = false)
        doNothing().`when`(local).setFavoriteTvShow(dataDummy,true)
        repository.setFavoriteTvShow(dataDummy,true)
        verify(local, times(1)).setFavoriteTvShow(dataDummy,true)
    }

}