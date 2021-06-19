package com.example.moviecatalogue.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.model.remote.entity.Movie
import com.example.moviecatalogue.model.remote.entity.TvShow
import com.example.moviecatalogue.model.remote.responsecall.RemoteDataSource
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.FakeRepository
import com.example.moviecatalogue.utils.LiveDataTestUtils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class RepositoryTest{

    private val listMovie = DataDummy.generateDataDummyCatalogueMovie()
    private var movieId = listMovie[0].id
    private val listTv = DataDummy.generateDataDummyCatalogueTv()
    private val tvId = listTv[0].id

    private val responseMovie = DataDummy.generateDataDummyCatalogueMovie()
    private val responseTv = DataDummy.generateDataDummyCatalogueTv()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val repository = FakeRepository(remote)
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getMovie() {
        val dummyMovie : MutableLiveData<List<Movie>> = MutableLiveData()
        dummyMovie.value = responseMovie
        Mockito.`when`(remote.getMovie()).thenReturn(dummyMovie)
        val result = LiveDataTestUtils.getValue(repository.getMovie())

        Mockito.verify(remote).getMovie()
        assertNotNull(result)
        assertEquals(responseMovie.size.toLong(), responseMovie.size.toLong())


    }

    @Test
    fun getTvShow() {
        val dummyTv : MutableLiveData<List<TvShow>> = MutableLiveData()
        dummyTv.value = responseTv
        Mockito.`when`(remote.getTvShow()).thenReturn(dummyTv)

        val result = LiveDataTestUtils.getValue(repository.getTvShow())
        Mockito.verify(remote).getTvShow()
        assertNotNull(result)
        assertEquals(responseTv.size.toLong(), responseTv.size.toLong())


    }

    @Test
    fun getMovieById() {
        val dummyMovieById : MutableLiveData<Movie> = MutableLiveData()
        dummyMovieById.value = listMovie[0]
        Mockito.`when`(movieId?.let { remote.getMovieById(it) }).thenReturn(dummyMovieById)

        val result = LiveDataTestUtils.getValue(repository.getMovieById(movieId ?: 0))

        Mockito.verify(remote).getMovieById(movieId ?: 0)
        assertNotNull(result)
        assertEquals(listMovie.size.toLong(), listMovie.size.toLong())


    }

    @Test
    fun getTvById() {
        val dummyTvById : MutableLiveData<TvShow> = MutableLiveData()
        dummyTvById.value = listTv[0]

        Mockito.`when`(tvId?.let { remote.getTvById(it) }).thenReturn(dummyTvById)

        val result = LiveDataTestUtils.getValue(repository.getTvById(tvId ?: 0))

        Mockito.verify(remote).getTvById(tvId ?: 0)
        assertNotNull(result)
        assertEquals(listTv.size.toLong(), listTv.size.toLong())


    }
}