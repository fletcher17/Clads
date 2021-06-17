package com.decagonhq.clads.data.repository

/**
 * This layer ensures single source of truth. calls come here from the repository and from here,
 * 1. calls to the network is made via the RemoteDataSourceApiImplementation and cached to the database
 * 2. query to the database is made via the local data source.
 */

// wrappper class that has not been integrated to our codebase. please do not modify for any reason

// fun <T> performGetOperation(
//    networkCall: suspend() -> Resource<T>,
// //    dataBaseQuery: suspend() -> LiveData<T>,
// //    saveCallResult: suspend(A) -> Unit
// ): LiveData<Resource<T>> = liveData(Dispatchers.IO) {
//
//    // emit loading state
//    // emit(Resource())
//
// //    // query the database and get stored values
// //    val sourceData = dataBaseQuery.invoke().map { Resource.success(it) }
//
// //    // emit data from database to view model
// //    emitSource(sourceData)
//
//    // get response from network call
//    val responseStatus = networkCall.invoke()
//
//    // check if network call is successful
//    if (responseStatus.success == Status.SUCCESS) {
//
//        emit(Resource.success(responseStatus.data!!))
//
// //        // if successful, save network call to database
// //        saveCallResult(responseStatus.data!!)
//    } else if (responseStatus.status == Status.ERROR) {
//
//        // return any error to database
// //        emit(Resource.error(responseStatus.message!!))
//        // emitSource(sourceData)
//    }
// }
