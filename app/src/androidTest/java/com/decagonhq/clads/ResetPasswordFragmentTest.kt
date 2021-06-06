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
import com.decagonhq.clads.ui.view.authenticationfragments.ResetPasswordFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class ResetPasswordFragmentTest {

    private lateinit var scenario: FragmentScenario<ResetPasswordFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Clads)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun is_clad_logo_visible() {
        onView(withId(R.id.reset_password_verification_fragment_clads_logo)).check(matches(isDisplayed()))
    }

    @Test
    fun is_view_in_browser_visible() {
        onView(withId(R.id.reset_password_verification_fragment_view_in_browser_textview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_reset_password_confirmation_logo_visible() {
        onView(withId(R.id.reset_password_verification_fragment_message_logo_imageview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_reset_password_confirmation_title_visible() {
        onView(withId(R.id.reset_password_verification_fragment_reset_password_textView)).check(matches(isDisplayed()))
    }

    @Test
    fun is_reset_password_confirmation_message_visible() {
        onView(withId(R.id.reset_password_verification_fragment_almost_there_message_textview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_verify_reset_password_button_visible() {
        onView(withId(R.id.reset_password_verification_fragment_reset_password_button)).check(matches(isDisplayed()))
    }

    @Test
    fun is_stay_in_touch_motto_visible() {
        onView(withId(R.id.reset_password_verification_fragment_stay_in_touch_textview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_facebook_logo_visible() {
        onView(withId(R.id.reset_password_verification_fragment_facebook_logo_imageview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_twitter_logo_visible() {
        onView(withId(R.id.reset_password_verification_fragment_twitter_logo_imageview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_instagram_logo_visible() {
        onView(withId(R.id.reset_password_verification_fragment_instagram_logo_imageview)).check(matches(isDisplayed()))
    }

    @Test
    fun is_email_logo_visible() {
        onView(withId(R.id.reset_password_verification_fragment_mail_imageview)).check(matches(isDisplayed()))
    }

    @Test
    fun does_reset_password_button_navigate_to_login_screen() {
        val mockNavController = mock(NavController::class.java)
        val resetPasswordConfirmation = launchFragmentInContainer<ResetPasswordFragment>()
        resetPasswordConfirmation.onFragment {
            Navigation.setViewNavController(it.requireView(), mockNavController)
        }
        onView(withId(R.id.reset_password_verification_fragment_reset_password_button)).perform(click())
        verify(mockNavController).navigate(R.id.login_fragment)
    }
}
