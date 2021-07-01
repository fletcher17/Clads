package com.decagonhq.clads

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagonhq.clads.ui.view.authenticationfragments.EmailConfirmationFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
@RunWith(AndroidJUnit4::class)
class EmailConfirmationFragmentTest {

    private lateinit var scenario: FragmentScenario<EmailConfirmationFragment>
    // test setup for the views on the fragment
    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Clads)
        scenario.moveToState(Lifecycle.State.STARTED)
    }
    // test for nested layout visibility
    @Test
    fun is_clad_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_nested_scroll_view_layout)).check(matches(isDisplayed()))
    }
    // test for textview visibility
    @Test
    fun is_view_in_browser_visible() {
        onView(withId(R.id.fragment_email_confirmation_view_in_browser_link_text_view)).check(
            matches(
                isDisplayed()
            )
        )
    }
    // test for image visibility
    @Test
    fun is_email_confirmation_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_email_confirmation_logo_image_view)).check(
            matches(
                isDisplayed()
            )
        )
    }
    // test for textview visibility
    @Test
    fun is_email_confirmation_title_visible() {
        onView(withId(R.id.fragment_email_confirmation_email_confirmation_title_text_view)).check(
            matches(
                isDisplayed()
            )
        )
    }
    // test for textview visibility
    @Test
    fun is_email_confirmation_message_visible() {
        onView(withId(R.id.fragment_email_confirmation_email_confirmation_message_text_view)).check(
            matches(
                isDisplayed()
            )
        )
    }
    // test for button visibility
    @Test
    fun is_verify_email_button_visible() {
        onView(withId(R.id.fragment_email_confirmation_email_verification_button)).check(
            matches(
                isDisplayed()
            )
        )
    }
    // test for textview visibility
    @Test
    fun is_stay_in_touch_motto_visible() {
        onView(withId(R.id.fragment_email_confirmation_stay_in_touch_motto_text_view)).check(
            matches(
                isDisplayed()
            )
        )
    }
    // test for image view visibility
    @Test
    fun is_facebook_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_facebook_link_logo_image_view)).check(
            matches(
                isDisplayed()
            )
        )
    }
    // test for image view visibility
    @Test
    fun is_twitter_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_twitter_link_logo_image_view)).check(matches(isDisplayed()))
    }
    // test for image view visibility
    @Test
    fun is_instagram_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_instagram_link_logo_image_view)).check(
            matches(
                isDisplayed()
            )
        )
    }
    // test for image view visibility
    @Test
    fun is_email_logo_visible() {
        onView(withId(R.id.fragment_email_confirmation_email_link_logo_image_view)).check(matches(isDisplayed()))
    }
    // test for button performance to navigate to login fragment screen when clicked
    @Test
    fun does_button_navigate_to_login_screen() {
        val mockNavController = mock(NavController::class.java)
        val emailConfirmationScenario = launchFragmentInContainer<EmailConfirmationFragment>()

        onView(withId(R.id.fragment_email_confirmation_email_verification_button)).perform(click())
        verify(mockNavController).navigate(R.id.login_fragment)
    }
}
