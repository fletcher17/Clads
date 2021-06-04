package com.decagonhq.clads.utils.validator

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class EmailAndPasswordValidatorTest {

    @Test
    fun `Enter empty email address field returns false`() {
        val email = ""
        val result = EmailAndPasswordValidator.isEmailValid(email)

        assertThat(result, `is`(false))
    }

    @Test
    fun `Enter valid email address returns true`() {
        val email = "oshiole@gmail.com"

        val result = EmailAndPasswordValidator.isEmailValid(email)

        assertThat(result, `is`(true))
    }

    @Test
    fun `Enter email that is not valid returns false`() {
        val input = "patty.com"
        val output = EmailAndPasswordValidator.isEmailValid(input)

        assertThat(output, `is`(false))
    }

    @Test
    fun `Enter password that is empty returns false`() {
        val password = ""
        val result = EmailAndPasswordValidator.isPasswordValid(password)

        assertThat(result, `is`(false))
    }

    @Test
    fun `Enter password that is not valid returns false`() {
        val password = "john"
        val result = EmailAndPasswordValidator.isPasswordValid(password)

        assertThat(result, `is`(false))
    }

    @Test
    fun `Enter valid password returns true`() {
        val password = "abubakar@49"
        val result = EmailAndPasswordValidator.isPasswordValid(password)

        assertThat(result, `is`(true))
    }

    @Test
    fun `Enter password that matches returns true`() {
        val password = "abubakar@49"
        val confirmPassword = "abubakar@49"

        val output = EmailAndPasswordValidator.isPasswordTheSame(password, confirmPassword)

        assertThat(output, `is`(true))
    }

    @Test
    fun `Enter password that is not the same returns false`() {
        val password = "john_peter@50"
        val confirmPassword = "john"

        val output = EmailAndPasswordValidator.isPasswordTheSame(password, confirmPassword)

        assertThat(output, `is`(false))
    }
}
