package com.decagonhq.clads.data.remote

import com.decagonhq.clads.data.entity.mappedmodel.*
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RemoteDataSourceApi {
    // HTTP POSTS AND GET REQUESTS
    @POST("artisans/register")
    suspend fun registerUser(@Body user: User): RegisterUserResponse

    // login with email request
    @POST("login")
    suspend fun loginUser(@Body loginUserResponse: UserLoginCredentials):
        LoginUserResponse

    @POST("login/google")
    suspend fun loginWithGoogle(
        @Header("Authorization") header: String,
        @Body loginWithGoogleInput: LoginWithGoogleCredentialsModel
    ): LoginUserResponse
}
