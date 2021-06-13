package com.decagonhq.clads.ui.view.cliientmanagementfragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.decagonhq.clads.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ClientHomeFragmentTest : TestCase() {
    @Before
    fun fragment_Set_Up_to_display_test() {
        val scenario =

            launchFragmentInContainer<ClientHomeFragment>(themeResId = R.style.Base_Theme_MaterialComponents)
    }

    @Test
    fun client_home_screen_title_text() {
        onView(withId(R.id.client_list_screen_title)).check(matches(isDisplayed()))
    }

    @Test
    fun client_home_screen_add_client_fab() {
        onView(withId(R.id.client_home_screen_add_client_fab)).check(matches(isDisplayed()))
    }

    @Test
    fun client_home_screen_placeholder_image_layout() {
        onView(withId(R.id.client_list_home_image_text_placeholder)).check(matches(isDisplayed()))
    }

    @Test
    fun client_home_screen_placeholder_text() {
        onView(withId(R.id.client_list_home_image_text_placeholder)).check(matches(isDisplayed()))
    }
}
