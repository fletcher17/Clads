package com.decagonhq.clads

import com.decagonhq.clads.utils.validator.SignUpFormValidation
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

class SignUpFormValidationTest {

    @Test
    fun validNamesFieldValidation() {
        val goodSignUpEmailAddress = "bawo@gmail.com"
        val result = SignUpFormValidation.emailValidator(goodSignUpEmailAddress)
        assertTrue(result)
    }

    @Test
    fun invalidNamesFieldValidation() {
        val badSignUpEmailAddress = "nelson.com"
        val result = SignUpFormValidation.emailValidator(badSignUpEmailAddress)
        assertFalse(result)
    }

    @Test
    fun validPasswordValidation() {
        val goodSignUpPassword = "cladislife"
        val result = SignUpFormValidation.passwordValidator(goodSignUpPassword)
        assertTrue(result)
    }

    @Test
    fun invalidPasswordValidation() {
        val badSignUpPassword = "deca"
        val result = SignUpFormValidation.passwordValidator(badSignUpPassword)
        assertFalse(result)
    }

    @Test
    fun validPasswordsComparisonValidation() {
        val password = "iwillbefine"
        val confirmPassword = "iwillbefine"
        val result = SignUpFormValidation.comparePasswords(password, confirmPassword)
        assertTrue(result)
    }

    @Test
    fun invalidPasswordsComparisonValidation() {
        val password = "willbefine"
        val confirmPassword = "iwillbefine"
        val result = SignUpFormValidation.comparePasswords(password, confirmPassword)
        assertFalse(result)
    }

    @Test
    fun validSignUpNameValidation() {
        val validSignUpName = "Nelscene"
        val result = SignUpFormValidation.nameValidator(validSignUpName)
        assertTrue(result)
    }

    @Test
    fun invalidSignUpNameValidation() {
        val invalidSignUpName = ""
        val result = SignUpFormValidation.nameValidator(invalidSignUpName)
        assertFalse(result)
    }
}
