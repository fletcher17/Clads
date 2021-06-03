package com.decagonhq.clads

import java.util.regex.Pattern

class SignUpFormValidation {

    companion object {

        fun emailValidator(emailAddress: String): Boolean {
            val validateEmailPattern: Pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
            val matchEmailPattern = validateEmailPattern.matcher(emailAddress)
            return matchEmailPattern.matches() && emailAddress.isNotEmpty()
        }

        fun comparePasswords(password: String, confirmPassword: String): Boolean {
            return password == confirmPassword
        }

        fun passwordValidator(password: String): Boolean {
            val validatePasswordPattern: Pattern = Pattern.compile(".{6,}")
            val matchEmailPattern = validatePasswordPattern.matcher(password)
            return matchEmailPattern.matches() && password.isNotEmpty()
        }

        fun nameValidator(name: String): Boolean {
            return name.isNotEmpty()
        }

        fun spinnerValidator(category: String): Boolean {
            val categoryPlaceHolder = "Choose account category"
            if (category != categoryPlaceHolder) {
            }
            return category.isNotEmpty()
        }
    }
}
