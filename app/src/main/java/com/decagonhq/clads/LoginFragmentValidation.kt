package com.decagonhq.clads

import java.util.regex.Matcher
import java.util.regex.Pattern

object LoginFragmentValidation {

    fun emailValidator(email: String): Boolean {
        var p: Pattern = Pattern.compile(
            "(^[A-Za-z]+[0-9A-Za-z._\\-]*)+@[a-z]+.[a-z]+$"
        )
        var m: Matcher = p.matcher(email)
        return m.matches()
    }

    fun passwordValidator(email: String): Boolean {
        var p: Pattern = Pattern.compile(
            "[a-zA-Z0-9]{6,}"
        )
        var m: Matcher = p.matcher(email)
        return m.matches()
    }
}
