package com.decagonhq.clads

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class EmailConfirmationFragmentTest {

    private lateinit var scenario: FragmentScenario<EmailConfirmationFragment>


    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Clads)
        scenario.moveToState(Lifecycle.State.STARTED)
    }


    @Test
    fun is_clad_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_nested_scroll_view_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun is_view_in_browser_visible() {
        onView(withId(R.id.fragment_email_confirmation_view_in_browser_link)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun is_email_confirmation_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_email_confirmation_logo)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun is_email_confirmation_title_visible() {
        onView(withId(R.id.fragment_email_confirmation_email_confirmation_title)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun is_email_confirmation_message_visible() {
        onView(withId(R.id.fragment_email_confirmation_email_confirmation_message)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun is_verify_email_button_visible() {
        onView(withId(R.id.fragment_email_confirmation_email_verification_button)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun is_stay_in_touch_motto_visible() {
        onView(withId(R.id.fragment_email_confirmation_stay_in_touch_motto)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun is_facebook_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_facebook_link_logo)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun is_twitter_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_twitter_link_logo)).check(matches(isDisplayed()))
    }

    @Test
    fun is_instagram_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_instagram_link_logo)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun is_email_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_email_link_logo)).check(matches(isDisplayed()))
    }

    @Test
    fun does_button_navigate_to_login_screen() {
        val mockNavController = mock(NavController::class.java)

        val emailConfirmationScenario = launchFragmentInContainer<EmailConfirmationFragment>()

        emailConfirmationScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), mockNavController)

        }

        onView(withId(R.id.fragment_email_confirmation_email_verification_button)).perform(click())
        verify(mockNavController).navigate(R.id.login_fragment)
    }
}