package com.decagonhq.clads

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagonhq.clads.ui.view.authenticationfragments.SignUpOptionsFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class SignUpOptionsFragmentUITest {

    @Test
    fun launch_Signup_Options_Fragment_And_Verify_Root_Layout_Is_Displayed() {

        launchFragmentInContainer<SignUpOptionsFragment>(themeResId = R.style.Theme_Clads)
        onView(withId(R.id.fragment_signup_options_root_layout_nested_scroll_view)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun launch_Signup_Options_Fragment_And_Verify_Clads_Logo_Is_Displayed() {

        launchFragmentInContainer<SignUpOptionsFragment>(themeResId = R.style.Theme_Clads)
        val cladsLogo = onView(withId(R.id.fragment_signup_options_clads_logo_image_view))

        cladsLogo.check(matches(isDisplayed()))
    }

    @Test
    fun launch_Signup_Options_Fragment_And_Verify_Signup_with_Google_Button_Is_Displayed_And_Clickable() {

        launchFragmentInContainer<SignUpOptionsFragment>(themeResId = R.style.Theme_Clads)
        val signUpWithGoogleButton = onView(withId(R.id.fragment_signup_options_signup_with_google_button))

        signUpWithGoogleButton.check(matches(isDisplayed()))
        signUpWithGoogleButton.check(matches(isClickable()))
    }

    @Test
    fun launch_Signup_Options_Fragment_And_Verify_SignUp_With_Email_Button_Is_Displayed_And_Clickable() {

        launchFragmentInContainer<SignUpOptionsFragment>(themeResId = R.style.Theme_Clads)
        val signUpWithEmailButton = onView(withId(R.id.fragment_signup_options_signup_with_email_button))

        signUpWithEmailButton.check(matches(isDisplayed()))
        signUpWithEmailButton.check(matches(isClickable()))
    }

    @Test
    fun launch_Signup_Options_Fragment_And_Verify_Have_An_Account_Text_Is_Displayed_Correctly() {

        launchFragmentInContainer<SignUpOptionsFragment>(themeResId = R.style.Theme_Clads)
        val haveAnAccountText = onView(withId(R.id.fragment_signup_options_have_an_account_text_text_view))

        haveAnAccountText.check(matches(isDisplayed()))
        haveAnAccountText.check(matches(withText(R.string.signup_options_fragment_have_an_account_text)))
    }

    @Test
    fun launch_Signup_Options_Fragment_And_Verify_Login_Text_Is_Displayed_Correctly_And_Is_Clickable() {

        launchFragmentInContainer<SignUpOptionsFragment>(themeResId = R.style.Theme_Clads)
        val loginText = onView(withId(R.id.fragment_signup_options_login_text_text_view))

        loginText.check(matches(isDisplayed()))
        loginText.check(matches(withText(R.string.signup_options_fragment_login_text)))
        loginText.check(matches(isClickable()))
    }

    @Test
    fun launch_Signup_Options_Fragment_And_Click_Email_Signup_Button_And_Verify_Navigation_To_Email_Signup_Fragment() {

        val mockNavController = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<SignUpOptionsFragment>(themeResId = R.style.Theme_Clads)
        scenario.onFragment { fragment ->
            mockNavController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
        onView(withId(R.id.fragment_signup_options_signup_with_email_button)).perform(click())
        verify(mockNavController).navigate(R.id.action_sign_up_options_fragment_to_email_sign_up_fragment)
    }

    @Test
    fun launch_Signup_Options_Fragment_And_Click_Login_Text_And_Verify_Navigation_To_Login_Fragment() {

        val mockNavController = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<SignUpOptionsFragment>(themeResId = R.style.Theme_Clads)
        scenario.onFragment { fragment ->
            mockNavController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
        onView(withId(R.id.fragment_signup_options_login_text_text_view)).perform(click())
        verify(mockNavController).navigate(R.id.action_sign_up_options_fragment_to_login_fragment)
    }
}
