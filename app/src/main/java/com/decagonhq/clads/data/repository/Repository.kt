package com.decagonhq.clads.data.repository

import android.util.Log
import com.decagonhq.clads.data.entity.Post
import com.decagonhq.clads.data.remote.BaseDataSource
import com.decagonhq.clads.data.remote.RemoteDataSourceApi
import retrofit2.Response
import javax.inject.Inject

/**
 * method calls from view model to this repository goes to the dataAccessStrategy
 * which ensures local caching and single source of truth
 */

class Repository @Inject constructor(
    private val remoteDataSourceApi: RemoteDataSourceApi,
) : BaseDataSource() {

    // this is an example of how all calls are to be made
    //        suspend fun getPost(): List<Post> {
    //            return remoteDataSourceApi.getPosts()
    //        }



    // this will bw needed when we implement local persistence. leave it for now
    //    suspend fun getPost() = performGetOperation (
    //        networkCall = { getResult { remoteDataSourceApi.getPosts() } },
    //        dataBaseQuery = { localDataSource.getUserDao().getPost() },
    //        saveCallResult = { localDataSource.getUserDao().savePost() }
    //    )

}