package com.decagonhq.clads.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads.data.entity.mappedmodel.*
import com.decagonhq.clads.data.repository.Repository
import com.decagonhq.clads.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserManagementViewModel
@Inject constructor (private val repository: Repository) : ViewModel() {

    private var _userLiveData = MutableLiveData<Resource<RegisterUserResponse>>()
    var userLiveData: LiveData<Resource<RegisterUserResponse>> = _userLiveData

    private var _loginUserResponseMutableLiveData = MutableLiveData<Resource<LoginUserResponse>>()
    var loginUserResponseLiveData: LiveData<Resource<LoginUserResponse>> = _loginUserResponseMutableLiveData

    private var _loginUserWithGoogleResponseMutableLiveData = MutableLiveData<Resource<LoginUserResponse>>()
    var loginUserWithGoogleResponseLiveData: LiveData<Resource<LoginUserResponse>> = _loginUserWithGoogleResponseMutableLiveData

    /**
     * Heavy operation that cannot be done in the Main Thread
     */
    fun registerThisUser(user: User) {
        viewModelScope.launch {
            _userLiveData.value = repository.registerUser(user)
        }
    }

    fun loginThisUserViaEmail(loginCredentialsProvidedByUser: UserLoginCredentials) {
        viewModelScope.launch {
            _loginUserResponseMutableLiveData.value = repository.loginUser(loginCredentialsProvidedByUser)
        }
    }

    fun loginThisUserViaGoogle(header: String?, role: LoginWithGoogleCredentialsModel) {
        viewModelScope.launch {
            _loginUserWithGoogleResponseMutableLiveData.value = repository.loginWithGoogle(header!!, role)
        }
    }
}
