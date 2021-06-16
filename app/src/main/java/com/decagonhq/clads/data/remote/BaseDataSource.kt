package com.decagonhq.clads.data.remote

import android.util.Log
import com.decagonhq.clads.resource.Resource
import retrofit2.Response


/**
 * Any call made to the network from the data access strategy comes through this class to RemoteDataSourceImplementation.
 * which makes the actual api call
 */

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response .isSuccessful){

                // get response body if call is successful and return as a success resource
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            // return error if any
            return error("${response.code()} ${response.message()}")

        } catch (error: Exception) {
            // return exception
            return  error(error.message?: error.toString())
        }
    }

    // return error as error resource
    private fun <T> error(message: String) : Resource<T> {
        return Resource.error("Network call failed for the following reason :$message")
    }
}