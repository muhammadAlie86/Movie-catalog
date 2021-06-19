package com.example.moviecatalogue.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.api.Constant.Companion.LANGUAGE
import com.example.moviecatalogue.api.Constant.Companion.PAGE
import com.example.moviecatalogue.model.remote.entity.TvShow
import com.example.moviecatalogue.repositories.Repository
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.viewmodel.TvViewModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel : TvViewModel

    @get:Rule
    var instantTaskExecutorRule =  InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer : Observer<List<TvShow>>


    @Before
    fun setUp(){
        viewModel = TvViewModel(repository)
    }

    @Test
    fun getTvShow() {

        val dummyTv: List<TvShow> = DataDummy.generateDataDummyCatalogueTv()

        val listTv: MutableLiveData<List<TvShow>> = MutableLiveData()
        listTv.run {
            setValue(dummyTv)
        }

        Mockito.`when`(viewModel.getTvShow(BuildConfig.API_KEY, LANGUAGE, PAGE)).thenReturn(listTv)

        val tvEntities = viewModel.getTvShow(BuildConfig.API_KEY, LANGUAGE, PAGE).value
        Mockito.verify(repository).getTvShow(BuildConfig.API_KEY, LANGUAGE, PAGE)

        assertNotNull(tvEntities)
        assertEquals(5, dummyTv.size)

        viewModel.getTvShow(BuildConfig.API_KEY, LANGUAGE, PAGE).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTv)
    }

}