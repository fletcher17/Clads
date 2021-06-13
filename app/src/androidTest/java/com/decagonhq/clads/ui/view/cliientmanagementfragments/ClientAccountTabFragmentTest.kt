package com.decagonhq.clads.ui.view.cliientmanagementfragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagonhq.clads.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ClientAccountTabFragmentTest {

    companion object {
        const val FIRST_NAME = "Deca"
        const val LAST_NAME = "Gon"
        const val PHONE = "08183745139"
        const val EMAIL = "michaelisesele@gmail.com"
    }

    private lateinit var scenario: FragmentScenario<ClientAccountTabFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Clads)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    // text for first name visibility
    @Test
    fun is_first_name_text_displayed() {
        onView(withId(R.id.client_account_fragment_client_first_name_input))
            .perform(replaceText(FIRST_NAME)).check(matches(isDisplayed()))
    }

    // text for last name visibility
    @Test
    fun is_last_name_text_displayed() {
        onView(withId(R.id.client_account_fragment_client_last_name_input))
            .perform(replaceText(LAST_NAME)).check(matches(isDisplayed()))
    }

    // test for email text visibility
    @Test
    fun is_email_text_displayed() {
        onView(withId(R.id.client_account_fragment_client_email_input))
            .perform(replaceText(EMAIL)).check(matches(isDisplayed()))
    }

    // test for phone text visibility
    @Test
    fun is_phone_text_displayed() {
        onView(withId(R.id.client_account_fragment_client_phone_number_input))
            .perform(replaceText(PHONE)).check(matches(isDisplayed()))
    }

    // text for button visibility
    @Test
    fun is_button_displayed() {
        onView(withId(R.id.add_client_account_tab_next_button))
            .check(matches(isDisplayed()))
    }
}
