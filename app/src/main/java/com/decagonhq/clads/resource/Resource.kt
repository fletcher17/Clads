package com.decagonhq.clads.resource

import retrofit2.Response

/**
 * The resource data class wraps calls emitted from the repository through its Data Access Strategy
 * in trying to get data from remote or local sources. this wrapping provides information about the aftermath of the call made.
 * (success or error)
 */

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: Response<Any>?
    ) : Resource<Nothing>()
}
