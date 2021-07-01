package com.decagonhq.clads.data.repository

import com.decagonhq.clads.data.entity.mappedmodel.LoginWithGoogleCredentialsModel
import com.decagonhq.clads.data.entity.mappedmodel.User
import com.decagonhq.clads.data.entity.mappedmodel.UserLoginCredentials
import com.decagonhq.clads.data.entity.mappedmodel.UserProfileClass
import com.decagonhq.clads.data.remote.BaseRepository
import com.decagonhq.clads.data.remote.RemoteDataSourceApi
import javax.inject.Inject

/**
 * method calls from view model to this repository goes to the dataAccessStrategy
 * which ensures local caching and single source of truth
 */

class Repository @Inject constructor(
    private val remoteDataSourceApi: RemoteDataSourceApi
) : BaseRepository() {

    suspend fun registerUser(user: User) = safeApiCall {
        remoteDataSourceApi.registerUser(user)
    }

    suspend fun loginUser(loginCredentialsProvidedByUser: UserLoginCredentials) = safeApiCall {
        remoteDataSourceApi.loginUser(loginCredentialsProvidedByUser)
    }

    suspend fun loginWithGoogle(header: String, loginWithGoogleCredentials: LoginWithGoogleCredentialsModel) = safeApiCall {
        remoteDataSourceApi.loginWithGoogle(header, loginWithGoogleCredentials)
    }

    suspend fun updateUserProfile(header: String, newUserData: UserProfileClass) = safeApiCall {
        remoteDataSourceApi.updateUserProfile(header, newUserData)
    }

    suspend fun getUserProfile(header: String) = safeApiCall {
        remoteDataSourceApi.getUserProfile(header)
    }
}
