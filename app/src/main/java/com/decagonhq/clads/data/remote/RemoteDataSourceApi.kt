package com.decagonhq.clads.data.remote

import com.decagonhq.clads.data.entity.mappedmodel.RegisterUserResponse
import com.decagonhq.clads.data.entity.mappedmodel.User
import com.decagonhq.clads.resource.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteDataSourceApi {
    // HTTP POSTS AND GET REQUESTS

    // example request
    //    @GET("/posts")
    //    suspend fun getPosts(): List<Post>

    @POST("artisans/register")
    suspend fun registerUser(@Body user: User ): Resource<RegisterUserResponse>

//    @POST("artisans/register")
//    suspend fun registerUser(@Path("user") user: User ): Call<RegisterUserResponse>

}
