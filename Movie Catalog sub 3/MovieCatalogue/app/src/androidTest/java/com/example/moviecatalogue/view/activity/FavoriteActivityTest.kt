package com.example.moviecatalogue.view.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class FavoriteActivityTest{



    @Before
    fun setUp() {
        ActivityScenario.launch(FavoriteActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }


    @Test
    fun setViewPager(){
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeLeft())
    }

    @Test
    fun loadFavoriteMovie(){

        onView(withId(R.id.tabs)).perform(click())
        onView(withId(R.id.rv_movie_favorite))
            .check(matches(isDisplayed()))
    }
    @Test
    fun loadFavoriteTv(){

        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_tv_favorite))
            .check(matches(isDisplayed()))
    }
}