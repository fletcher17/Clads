package com.decagonhq.clads.utils.validator

import java.util.regex.Matcher
import java.util.regex.Pattern

object LoginFragmentValidation {

    fun emailValidator(email: String): Boolean {
        val p: Pattern = Pattern.compile(
            "(^[A-Za-z]+[0-9A-Za-z._\\-]*)+@[a-z]+.[a-z]+$"
        )
        val m: Matcher = p.matcher(email)
        return m.matches()
    }

    fun passwordValidator(email: String): Boolean {
        val p: Pattern = Pattern.compile(
            "[\\w\\W]{6,}"
        )
        val m: Matcher = p.matcher(email)
        return m.matches()
    }
}
