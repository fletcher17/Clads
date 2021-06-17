package com.decagonhq.clads.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads.data.entity.mappedmodel.RegisterUserResponse
import com.decagonhq.clads.data.entity.mappedmodel.User
import com.decagonhq.clads.data.repository.Repository
import com.decagonhq.clads.resource.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class UserManagementViewModel
@Inject constructor (private val repository: Repository) : ViewModel(){

    // example live data
    //    private val _postsLiveData = MutableLiveData<List<Post>>()
    //    val postsLiveData: LiveData<List<Post>> = _postsLiveData

        private var _userLiveData = MutableLiveData<RegisterUserResponse>()
        var userLiveData: MutableLiveData<RegisterUserResponse> = _userLiveData

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
    fun registerThisUser(user : User) {
        uiScope.launch {
            val response = repository.registerUser(user)
            withContext(Dispatchers.Main) {
                try {
                    if (response.status == Status.SUCCESS) {
                        //Do something with response e.g show to the UI.
                        userLiveData.value = response.data
                        Log.d("livedata", "$userLiveData")
                    } else {
                        Log.d("response error", "Error: ${response.status}")
                    }
                } catch (e: HttpException) {
                    Log.d("Exception","Exception ${e.message}")
                } catch (e: Throwable) {
                    Log.d("Trhowable","Ooops: Something else went wrong")
                }

                Log.d("value", userLiveData.value.toString())
            }
        }
    }

    //    /**
    //     * Cancel all coroutines when the ViewModel is cleared
    //     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}
