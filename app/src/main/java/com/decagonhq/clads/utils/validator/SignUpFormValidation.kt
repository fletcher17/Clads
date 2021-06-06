package com.decagonhq.clads.utils.validator

import java.util.regex.Pattern

class SignUpFormValidation {

    companion object {

        // validate email
        fun emailValidator(emailAddress: String): Boolean {
            val validateEmailPattern: Pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
            val matchEmailPattern = validateEmailPattern.matcher(emailAddress)
            return matchEmailPattern.matches() && emailAddress.isNotEmpty()
        }

        // validate password
        fun comparePasswords(password: String, confirmPassword: String): Boolean {
            return password == confirmPassword
        }

        // validate password
        fun passwordValidator(password: String): Boolean {
            val validatePasswordPattern: Pattern = Pattern.compile(".{6,}")
            val matchEmailPattern = validatePasswordPattern.matcher(password)
            return matchEmailPattern.matches() && password.isNotEmpty()
        }

        // validate name
        fun nameValidator(name: String): Boolean {
            return name.isNotEmpty()
        }

        // validate spinner
        fun spinnerValidator(category: String): Boolean {
            val categoryPlaceHolder = "Choose account category"
            return if (category == categoryPlaceHolder) {
                category.isEmpty()
            } else {
                category.isNotEmpty()
            }
        }
    }
}
