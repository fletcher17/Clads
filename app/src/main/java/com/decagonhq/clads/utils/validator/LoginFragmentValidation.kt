package com.decagonhq.clads.utils.validator

import com.decagonhq.clads.data.entity.mappedmodel.LoginUserResponse
import com.decagonhq.clads.resource.Resource
import java.util.regex.Matcher
import java.util.regex.Pattern

object LoginFragmentValidation {

    data class ValidationResponse(
        val is_Successful: Boolean?,
        val payload: String?
    )

    fun emailValidator(email: String): Boolean {
        val p: Pattern = Pattern.compile(
            "(^[A-Za-z]+[0-9A-Za-z._\\-]*)+@[a-z]+.[a-z]+$"
        )
        val m: Matcher = p.matcher(email)
        return m.matches()
    }

    fun userDetailsNetworkCallResponseValidation(response: Resource<LoginUserResponse>): ValidationResponse {
        return when (response) {
            is Resource.Success -> {
                if (response.value.status!! == 200) {
                    // User Authentication Token
                    val result = response.value.payload

                    ValidationResponse(true, result)
                } else {
                    ValidationResponse(false, "Invalid email and password combination")
                }
            } else -> {
                ValidationResponse(false, "Unable to connect to server")
            }
        }
    }
}
