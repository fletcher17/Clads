package com.decagonhq.clads.data.remote

import com.decagonhq.clads.data.entity.Post
import retrofit2.Response
import retrofit2.http.GET

interface RemoteDataSourceApi {
    // HTTP POSTS AND GET REQUESTS
    @GET("/DummyFoodiumApi/api/posts/")
    suspend fun getPosts(): Response<List<Post>>
}