package com.example.moviecatalogue.view.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainActivityTest {

    private val dataMovie = DataDummy.generateDataDummyCatalogueMovie()
    private val dataTv = DataDummy.generateDataDummyCatalogueTv()



    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }


    @Test
    fun setViewPager(){
        onView(withId(R.id.view_pager)).perform(swipeLeft())
    }
    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataMovie.size
            )
        )

        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, ViewActions.click()
            )
        )
    }

        @Test
        fun loadTvShow() {
            onView(withText(R.string.tv_show)).perform(ViewActions.click())
            onView(withId(R.id.rv_tv_show))
                .check(ViewAssertions.matches(isDisplayed()))

            onView(withId(R.id.rv_tv_show)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    dataTv.size)
            )
        }

}

