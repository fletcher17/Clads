package com.decagonhq.clads

import com.decagonhq.clads.utils.validator.LoginFragmentValidation
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoginFragmentUnitText {

    /**
     * FUNCTIONS FOR BUILDING THE TEST CASES START HERE
     */
    fun user_provided_correct_email_expected_to_pass_the_test(email: String) {

        // When
        val actual = LoginFragmentValidation.emailValidator(email)

        // Then
        assertTrue(actual)
    }

    fun user_provided_wrong_email_expected_to_fail_the_test(email: String) {

        // When
        val actual = LoginFragmentValidation.emailValidator(email)

        // Then
        assertTrue(actual)
    }

    fun users_password_is_greater_than_or_equal_to_6_characters_expected_to_pass_the_test(password: String) {

        // When
        val actual = LoginFragmentValidation.passwordValidator(password)

        // Then
        assertTrue(actual)
    }

    fun users_password_is_less_than_6_characters_expected_to_fail_the_test(password: String) {

        // When
        val actual = LoginFragmentValidation.passwordValidator(password)

        // Then
        assertFalse(actual)
    }

    @Test
    fun test_user_provided_correct_email_expected_to_pass_the_test() {
        user_provided_correct_email_expected_to_pass_the_test("adebayooloyede@gmail.com")
    }

    @Test
    fun test_user_provided_wrong_email_expected_to_fail_the_test() {
        user_provided_wrong_email_expected_to_fail_the_test("adebayooloyede@gmail")
    }

    @Test
    fun test_users_password_is_greater_than_or_equal_to_6_characters_expected_to_pass_the_test() {
        users_password_is_greater_than_or_equal_to_6_characters_expected_to_pass_the_test("ade56341hq")
    }

    @Test
    fun test_users_password_is_less_than_equal_to_6_characters_expected_to_pass_the_test() {
        users_password_is_less_than_6_characters_expected_to_fail_the_test("098lo")
    }
}
