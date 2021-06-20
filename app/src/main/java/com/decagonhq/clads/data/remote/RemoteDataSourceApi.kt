package com.decagonhq.clads.data.remote

import com.decagonhq.clads.data.entity.mappedmodel.RegisterUserResponse
import com.decagonhq.clads.data.entity.mappedmodel.User
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteDataSourceApi {
    // HTTP POSTS AND GET REQUESTS
    @POST("artisans/register")
    suspend fun registerUser(@Body user: User): RegisterUserResponse
}
