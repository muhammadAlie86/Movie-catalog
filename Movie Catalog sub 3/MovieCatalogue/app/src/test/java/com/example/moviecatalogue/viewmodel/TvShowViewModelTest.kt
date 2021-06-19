package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.local.entity.TvShow
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
class TvShowViewModelTest {

    private lateinit var viewModel : TvViewModel

    @get:Rule
    var instantTaskExecutorRule =  InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer : Observer<Resource<List<TvShow>>>


    @Before
    fun setUp(){
        viewModel = TvViewModel(repository)
    }

    @Test
    fun getTvShow() {

        val dummyTv: Resource<List<TvShow>> = Resource.success(DataDummy.generateDataDummyCatalogueTv())

        val listTv: MutableLiveData<Resource<List<TvShow>>> = MutableLiveData()
        listTv.run {
            setValue(dummyTv)
        }

        `when`(viewModel.getTvShow()).thenReturn(listTv)

        val tvEntities = viewModel.getTvShow().value?.data
        verify(repository).getAllTvShows()

        assertNotNull(tvEntities)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

}