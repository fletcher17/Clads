package com.decagonhq.clads

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.decagonhq.clads.ui.view.authenticationfragments.LoginFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginFragmentUiTest {

    private lateinit var scenario: FragmentScenario<LoginFragment>

    companion object {
        val email = "adebayoolawale@gmail.com"
        val password = "09375893olouhf"
    }

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_MaterialComponents)
    }

    fun is_view_visible(viewId: Int) {
        onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     *TEST CASES
     */
    @Test
    fun is_the_welcome_back_text_view_visible() {
        is_view_visible(R.id.login_screen_fragment_welcome_back_text_view)
    }

    @Test
    fun is_the_login_to_continue_text_view_visible() {
        is_view_visible(R.id.login_screen_fragment_login_to_continue_text_view)
    }

    @Test
    fun is_the_sign_in_with_google_button_visible() {
        is_view_visible(R.id.linearLayout3)
    }

    @Test
    fun is_the_email_edit_text_view_visible() {
        is_view_visible(R.id.login_screen_fragment_email_address_text_view)
    }

    @Test
    fun is_the_password_edit_text_view_visible() {
        is_view_visible(R.id.login_screen_fragment_password_text_view)
    }

    @Test
    fun is_the_login_button_visible() {
        is_view_visible(R.id.login_screen_fragment_login_button_text_view)
    }
    @Test
    fun is_the_new_user_text_view_visible() {
        is_view_visible(R.id.login_screen_fragment_asking_if_new_user_text_view)
    }

    @Test
    fun is_the_sign_up_for_free_text_view_visible() {
        is_view_visible(R.id.login_screen_fragment_sign_up_for_free_link_text_view)
    }

    @Test
    fun is_the_forgot_password_text_view_visible() {
        is_view_visible(R.id.login_screen_fragment_forgot_password_text_view)
    }

    @Test
    fun is_the_sign_in_with_google_button_clickable() {
        onView(withId(R.id.linearLayout3)).perform(click())
    }

    fun user_can_type_into_the_email_address_edit_text(email: String) {
        onView(withId(R.id.login_screen_fragment_email_address_text_view)).perform(
            ViewActions.typeText(
                email
            )
        )
    }

    fun user_can_type_into_the_password_edit_text(password: String) {
        onView(withId(R.id.login_screen_fragment_password_text_view)).perform(
            ViewActions.typeText(
                password
            )
        )
    }

    @Test
    fun is_the_login_button_clickable() {
        user_can_type_into_the_email_address_edit_text(email)
        user_can_type_into_the_password_edit_text(password)
        onView(withId(R.id.login_screen_fragment_login_button_text_view)).perform(click())
    }
}
