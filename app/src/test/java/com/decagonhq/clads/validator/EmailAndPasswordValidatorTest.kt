package com.decagonhq.clads.validator

import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.Assert.*


class EmailAndPasswordValidatorTest {

    @Test
    fun isEmailEmpty_returnFalse() {
        val email = ""
        val result = EmailAndPasswordValidator().isEmailValid(email)

        assertThat(result, `is`(false))
    }

    @Test
    fun isEmailValid_returnTrue() {
        val email = "oshiole@gmail.com"

        val result = EmailAndPasswordValidator().isEmailValid(email)

        assertThat(result, `is`(true))
    }

    @Test
    fun `Enter email that is not valid return false`() {
        val input = "patty.com"
        val output = EmailAndPasswordValidator().isEmailValid(input)

        assertThat(output, `is`(false))
    }

    @Test
    fun isPasswordEmpty_returnFalse() {
        val password = ""
        val result = EmailAndPasswordValidator().isPasswordValid(password)

        assertThat(result, `is`(false))
    }

    @Test
    fun isPasswordNotValid_returnFalse() {
        val password = "john"
        val result = EmailAndPasswordValidator().isPasswordValid(password)

        assertThat(result, `is`(false))
    }


    @Test
    fun isPasswordValid_returnTrue() {
        val password = "abubakar@49"
        val result = EmailAndPasswordValidator().isPasswordValid(password)

        assertThat(result, `is`(true))
    }


    @Test
    fun isPasswordSame_returnTrue() {
        val password = "abubakar@49"
        val confirmPassword = "abubakar@49"

        val output = EmailAndPasswordValidator().isPasswordTheSame(password, confirmPassword)

        assertThat(output, `is`(true))
    }


    @Test
    fun isPasswordNotTheSame_returnFalse() {
        val password = "john_peter@50"
        val confirmPassword = "john"

        val output = EmailAndPasswordValidator().isPasswordTheSame(password, confirmPassword)

        assertThat(output, `is`(false))
    }
}