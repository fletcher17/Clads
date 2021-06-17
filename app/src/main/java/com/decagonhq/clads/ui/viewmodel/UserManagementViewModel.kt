package com.decagonhq.clads.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads.data.entity.mappedmodel.RegisterUserResponse
import com.decagonhq.clads.data.entity.mappedmodel.User
import com.decagonhq.clads.data.remote.Resource
import com.decagonhq.clads.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserManagementViewModel
@Inject constructor (private val repository: Repository) : ViewModel() {

    private var _userLiveData = MutableLiveData<Resource<RegisterUserResponse>>()
    var userLiveData: LiveData<Resource<RegisterUserResponse>> = _userLiveData

    /**
     * Heavy operation that cannot be done in the Main Thread
     */
    fun registerThisUser(user: User) {

        viewModelScope.launch {
            _userLiveData.value = repository.registerUser(user)
        }
    }
}
