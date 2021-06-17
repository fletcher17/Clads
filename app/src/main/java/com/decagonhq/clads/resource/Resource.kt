package com.decagonhq.clads.resource

/**
 * The resource data class wraps calls emitted from the repository through its Data Access Strategy
 * in trying to get data from remote or local sources. this wrapping provides information about the aftermath of the call made.
 * (success or error)
 */

// wrapper class that has not been integrated to our codebase. please do not modify for any reason

data class Resource <out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
