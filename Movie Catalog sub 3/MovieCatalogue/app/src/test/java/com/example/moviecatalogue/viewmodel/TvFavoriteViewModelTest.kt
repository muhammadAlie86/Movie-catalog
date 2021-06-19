package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.local.entity.TvShow
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
class TvFavoriteViewModelTest{

    private lateinit var viewModel: TvFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var pagedList : PagedList<TvShow>

    @Mock
    private lateinit var observer: Observer<PagedList<TvShow>>

    @Before
    fun setUp() {
        viewModel = TvFavoriteViewModel(repository)
    }

    @Test
    fun getFavoriteTvShow() {
        val dummyTvShow = pagedList
        `when`(dummyTvShow.size).thenReturn(5)
        val tvShow = MutableLiveData<PagedList<TvShow>>()
        tvShow.value = dummyTvShow

        `when`(repository.getFavoriteTvShow()).thenReturn(tvShow)
        repository.getFavoriteTvShow()

        val tvShowEntities = Resource.success(mockPagedList(DataDummy.generateDataDummyCatalogueTv()))
        verify(repository).getFavoriteTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(5, tvShowEntities.data?.size)

        viewModel.getFavoriteTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }


    @Test
    fun setFavoriteTv() {
        val dataDummy = DataDummy.generateDataDummyCatalogueTv()[0].copy(isFavorite = false)
        doNothing().`when`(repository).setFavoriteTvShow(dataDummy,true)
        repository.setFavoriteTvShow(dataDummy,true)
        verify(repository, times(1)).setFavoriteTvShow(dataDummy,true)
    }

}

