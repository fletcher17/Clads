package com.decagonhq.clads.utils.validator

class EmailAndPasswordValidator {
    /**
     * [isEmailValid] is used to validate the user email
     * @param email is the user given email
     * @return true if email is valid
     * @return false if otherwise
     * */
    companion object {

        fun isEmailValid(email: String): Boolean {
            val pattern = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}".toRegex()
            return email.matches(pattern)
        }

        /**
         * [isPasswordValid] is used to validate the user email
         * @param password is the user given email
         * @return true if password is valid and not empty
         * @return false if otherwise
         * */

        fun isPasswordValid(password: String): Boolean {
            return password.isNotEmpty() && password.length > 6
        }

        /**
         * [isPasswordTheSame] is used to validate the user email
         * @param password is the user given email
         * @param confirmPassword
         * @return true if both password are the same
         * @return false if otherwise
         * */
        fun isPasswordTheSame(password: String, confirmPassword: String): Boolean {
            return password == confirmPassword
        }
    }
}
