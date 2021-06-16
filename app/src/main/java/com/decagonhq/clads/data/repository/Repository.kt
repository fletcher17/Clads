package com.decagonhq.clads.data.repository

import com.decagonhq.clads.data.local.LocalDataSource
import com.decagonhq.clads.data.remote.BaseDataSource
import com.decagonhq.clads.data.remote.RemoteDataSourceApi
import javax.inject.Inject

/**
 * method calls from view model to this repository goes to the dataAccessStrategy
 * which ensures local caching and single source of truth
 */

class Repository @Inject constructor(
    private val remoteDataSourceApi: RemoteDataSourceApi,
    private val localDataSource: LocalDataSource,
) : BaseDataSource() {

    suspend fun getPost() = performGetOperation (
        networkCall = { getResult { remoteDataSourceApi.getPosts() } },
        dataBaseQuery = { localDataSource.getUserDao().getPost() },
        saveCallResult = { localDataSource.getUserDao().savePost() }
    )

}