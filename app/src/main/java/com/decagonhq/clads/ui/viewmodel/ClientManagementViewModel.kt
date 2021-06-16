package com.decagonhq.clads.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads.data.entity.Post
import com.decagonhq.clads.data.repository.Repository
import com.decagonhq.clads.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class ClientManagementViewModel @Inject constructor (private val repository: Repository): ViewModel() {

    // example live data
    //    private val _postsLiveData = MutableLiveData<List<Post>>()
    //    val postsLiveData: LiveData<List<Post>> = _postsLiveData


    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    /**
     * Heavy operation that cannot be done in the Main Thread
     */
    fun launchDataLoad() {
        uiScope.launch {
            // example method
            fetchPost() // happens on the background
            // Modify UI
        }
    }

    // Move the execution off the main thread using withContext(Dispatchers.Default)
    // example method for network call
    suspend fun fetchPost() = withContext(Dispatchers.Main) {
        // postsLiveData.value = repository.getPost()
    }

//    /**
//     * Cancel all coroutines when the ViewModel is cleared
//     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}