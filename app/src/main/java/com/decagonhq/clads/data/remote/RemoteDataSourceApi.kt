package com.decagonhq.clads.data.remote

import com.decagonhq.clads.data.entity.mappedmodel.LoginUserResponse
import com.decagonhq.clads.data.entity.mappedmodel.LoginWithGoogleCredentialsModel
import com.decagonhq.clads.data.entity.mappedmodel.RegisterUserResponse
import com.decagonhq.clads.data.entity.mappedmodel.ResponseFromGetAndUpdateUserProfileRequest
import com.decagonhq.clads.data.entity.mappedmodel.User
import com.decagonhq.clads.data.entity.mappedmodel.UserLoginCredentials
import com.decagonhq.clads.data.entity.mappedmodel.UserProfileClass
import com.decagonhq.clads.data.model.UserProfileModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
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

    @PATCH
    suspend fun updateUserProfile(
        @Header("Authorization") header: String,
        @Body newUserData: UserProfileClass
    ): ResponseFromGetAndUpdateUserProfileRequest

    @GET("me/profile")
    suspend fun getUserProfile(
        @Header("Authorization") header: String,
    ): UserProfileModel
}
