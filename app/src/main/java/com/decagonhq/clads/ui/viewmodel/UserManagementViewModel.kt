package com.decagonhq.clads.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads.data.entity.mappedmodel.LoginUserResponse
import com.decagonhq.clads.data.entity.mappedmodel.LoginWithGoogleCredentialsModel
import com.decagonhq.clads.data.entity.mappedmodel.RegisterUserResponse
import com.decagonhq.clads.data.entity.mappedmodel.ResponseFromGetAndUpdateUserProfileRequest
import com.decagonhq.clads.data.entity.mappedmodel.UploadPhotoResponse
import com.decagonhq.clads.data.entity.mappedmodel.User
import com.decagonhq.clads.data.entity.mappedmodel.UserLoginCredentials
import com.decagonhq.clads.data.entity.mappedmodel.UserProfileClass
import com.decagonhq.clads.data.repository.Repository
import com.decagonhq.clads.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserManagementViewModel
@Inject constructor (private val repository: Repository) : ViewModel() {

    private var _imageUploadLiveData = MutableLiveData<Resource<UploadPhotoResponse>>()
    var imageUploadLiveData: LiveData<Resource<UploadPhotoResponse>> = _imageUploadLiveData

    private val _userProfileMutableLiveData = MutableLiveData<UserProfileClass>()
    val userProfileLiveData: LiveData<UserProfileClass> get() = _userProfileMutableLiveData

    private val _responseFromGetAndUpdateUserProfileRequest = MutableLiveData<Response<ResponseFromGetAndUpdateUserProfileRequest>>()
    val responseFromGetAndUpdateUserProfileRequest: LiveData<Response<ResponseFromGetAndUpdateUserProfileRequest>> = _responseFromGetAndUpdateUserProfileRequest

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

    fun updateUserProfileOnRemoteServer(header: String, profile: UserProfileClass) {
        viewModelScope.launch {
            val responseFromPatchRequest = repository.updateUserProfile(header, profile)
        }
    }

    fun updateUsersNewProfileObjectBeforeSendingToRemoteServer(newProfileObject: UserProfileClass?) {
        when {
            newProfileObject!!.country!!.isNotEmpty() -> {
                _userProfileMutableLiveData.value!!.country = newProfileObject.country
            }
            newProfileObject.email!!.isNotEmpty() -> {
                _userProfileMutableLiveData.value!!.email = newProfileObject.email
            }
            newProfileObject.firstName!!.isNotEmpty() -> {
                _userProfileMutableLiveData.value!!.firstName = newProfileObject.firstName
            }
            newProfileObject.gender!!.isNotEmpty() -> {
                _userProfileMutableLiveData.value!!.gender = newProfileObject.gender
            }
            newProfileObject.id!! > 0 -> {
                _userProfileMutableLiveData.value!!.id = newProfileObject.id
            }
            newProfileObject.lastName!!.isNotEmpty() -> {
                _userProfileMutableLiveData.value!!.lastName = newProfileObject.lastName
            }
            newProfileObject.phoneNumber!!.isNotEmpty() -> {
                _userProfileMutableLiveData.value!!.phoneNumber = newProfileObject.phoneNumber
            }
            newProfileObject.role!!.isNotEmpty() -> {
                _userProfileMutableLiveData.value!!.role = newProfileObject.role
            }
            newProfileObject.showroomAddress != null -> {
                _userProfileMutableLiveData.value!!.showroomAddress = newProfileObject.showroomAddress
            }
            newProfileObject.thumbnail!!.isNotEmpty() -> {
                _userProfileMutableLiveData.value!!.thumbnail = newProfileObject.thumbnail
            }
            newProfileObject.union != null -> {
                _userProfileMutableLiveData.value!!.union = newProfileObject.union
            }
            newProfileObject.workshopAddress != null -> {
                _userProfileMutableLiveData.value!!.workshopAddress = newProfileObject.workshopAddress
            }
        }
    }
}
