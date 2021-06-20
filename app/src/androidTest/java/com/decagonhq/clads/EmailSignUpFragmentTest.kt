package com.decagonhq.clads

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.decagonhq.clads.ui.view.activity.MainActivity
import com.decagonhq.clads.ui.view.authenticationfragments.EmailSignUpFragment
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class EmailSignUpFragmentTest : TestCase() {
    @Before
    fun fragment_Set_Up() {
        val scenario =

            launchFragmentInContainer<EmailSignUpFragment>(themeResId = R.style.Base_Theme_MaterialComponents)
    }

    @Test
    fun is_Email_Sign_Up_Fragment_Sign_Up_Screen_In_View() {
        onView(withId(R.id.fragment_email_sign_up_screen_layout))
            .check(matches(isDisplayed()))
    }
    @Test
    fun create_An_Account_Visible() {
        onView(withId(R.id.fragment_email_sign_up_create_an_account_text_view)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun email_Sign_Up_Fragment_First_Name_In_View() {
        onView(withId(R.id.fragment_email_sign_up_screen_first_name_edit_text))
            .check(
                matches(isDisplayed())
            )
    }

    @Test
    fun email_Sign_Up_Fragment_Sign_Up_Button_In_View() {
        onView(withId(R.id.fragment_email_sign_up_nested_scroll_view)).perform(swipeUp())
        onView(withId(R.id.fragment_email_sign_up_screen_sign_up_button)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun new_User_Sign_Up_Test() {
        val activityTest = ActivityScenario.launch(MainActivity::class.java)

        // DATA INPUT FOR UI TESTING
        val firstName = "Bawo"
        val lastName = "Nelson"
        val accountCategory = "Tailor"
        val emailAddress = "bawonelson@gmail.com"
        val password = "Decadev"
        val confirmPassword = "Decadev"

        // TESTING UI COMPONENTS
        onView(withId(R.id.fragment_email_sign_up_screen_first_name_edit_text)).perform(typeText(firstName))
        onView(withId(R.id.fragment_email_sign_up_screen_last_name_edit_text)).perform(typeText(lastName))
        onView(withId(R.id.fragment_email_sign_up_screen_email_address_edit_text)).perform(typeText(emailAddress))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.fragment_email_sign_up_screen_account_category_filled_dropdown)).perform(replaceText(accountCategory))
        onView(withId(R.id.fragment_email_sign_up_screen_confirm_password_edit_text)).perform(typeText(password))
        onView(withId(R.id.fragment_email_sign_up_screen_confirm_password_edit_text)).perform(typeText(confirmPassword))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
