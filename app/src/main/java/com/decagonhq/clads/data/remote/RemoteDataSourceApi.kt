package com.decagonhq.clads.data.remote

import android.net.Uri
import com.decagonhq.clads.data.entity.mappedmodel.ImageProfilePayload
import com.decagonhq.clads.data.entity.mappedmodel.RegisterUserResponse
import com.decagonhq.clads.data.entity.mappedmodel.UploadPhotoResponse
import com.decagonhq.clads.data.entity.mappedmodel.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface RemoteDataSourceApi {
    // HTTP POSTS AND GET REQUESTS
    @POST("artisans/register")
    suspend fun registerUser(@Body user: User): RegisterUserResponse


}
