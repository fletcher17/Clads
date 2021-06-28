package com.decagonhq.clads.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads.data.entity.Profile
import com.decagonhq.clads.data.entity.Specialty
import com.decagonhq.clads.utils.GOOGLE_SIGN_IN_REQUEST_CODE

class EditProfileFragmentViewModel : ViewModel() {

    val listOfSpecialty = MutableLiveData<ArrayList<Specialty>>()
    private val _updatedProfile = MutableLiveData<Profile>()
    val updatedProfile: LiveData<Profile> get() = _updatedProfile

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val otherName = MutableLiveData<String>()
    val gender = MutableLiveData<String>()
    val workShopAddress = MutableLiveData<String>()
    val showRoomAddress = MutableLiveData<String>()
    val numberOfEmployees = MutableLiveData<String>()
    val legalStatus = MutableLiveData<String>()
    val nameOfUnion = MutableLiveData<String>()
    val ward = MutableLiveData<String>()
    val localGovtArea = MutableLiveData<String>()
    val state = MutableLiveData<String>()
    val cladsTrained = MutableLiveData<String>()
    val deliveryLeadTime = MutableLiveData<String>()

    init {
        getDefaultList()
        getUserProfile()
    }

    private fun getDefaultList() {
        listOfSpecialty.value = Specialty.getDefaultSpecialityList()
    }

    fun addToSpecialtyList(specialty: Specialty) {
        if (specialty.name.isNotEmpty()) {
            val list = listOfSpecialty.value
            list?.add(0, (specialty))
            listOfSpecialty.value = list!!
        }
    }

    fun updateProfile(profile: Profile) {
        _updatedProfile.value = profile
    }

    private fun getUserProfile() {
        
    }
}
