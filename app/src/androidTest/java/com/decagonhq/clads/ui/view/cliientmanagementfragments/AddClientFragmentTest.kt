package com.decagonhq.clads.ui.view.cliientmanagementfragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagonhq.clads.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddClientFragmentTest {

    private lateinit var scenario: FragmentScenario<AddClientFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Clads)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    // check id add contact text view is displayed
    @Test
    fun is_add_contact_text_displayed() {
        onView(withId(R.id.add_client_fragment_add_client_text))
            .check(matches(isDisplayed()))
    }
}
