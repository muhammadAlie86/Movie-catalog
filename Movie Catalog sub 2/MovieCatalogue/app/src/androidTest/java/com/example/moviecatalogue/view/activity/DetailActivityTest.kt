package com.example.moviecatalogue.view.activity

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest{

    private lateinit var instrumentalContext: Context

    private val dataMovie = DataDummy.generateDataDummyCatalogueMovie()[0]
    private val dataTv = DataDummy.generateDataDummyCatalogueTv()[0]

    private fun getActivityIntentMovie() : Intent{
        instrumentalContext = InstrumentationRegistry.getInstrumentation().targetContext
        val result = Intent(instrumentalContext,DetailActivity::class.java)
        result.putExtra(DetailActivity.EXTRA_DATA,dataMovie.id)
        result.putExtra(DetailActivity.EXTRA_TYPE,DetailActivity.MOVIE_TYPE_ID)
        return result
    }
    private fun getActivityIntentTv() : Intent{
        instrumentalContext = InstrumentationRegistry.getInstrumentation().targetContext
        val result = Intent(instrumentalContext,DetailActivity::class.java)
        result.putExtra(DetailActivity.EXTRA_DATA,dataTv.id)
        result.putExtra(DetailActivity.EXTRA_TYPE,DetailActivity.TV_TYPE_ID)
        return result
    }


    @Before
    fun setUp() {
        ActivityScenario.launch<DetailActivity>(getActivityIntentMovie())
        ActivityScenario.launch<DetailActivity>(getActivityIntentTv())
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {

        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadDetailMovie(){

        onView(withId(R.id.img_poster)).perform(click())
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
    }
    @Test
    fun loadDetailTv(){

        onView(withId(R.id.img_poster)).perform(click())
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
    }
}

