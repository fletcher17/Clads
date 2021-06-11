package com.decagonhq.clads.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads.models.Profile
import com.decagonhq.clads.models.Specialty

class EditProfileFragmentViewModel : ViewModel() {

    val listOfSpecialty = MutableLiveData<ArrayList<Specialty>>()
    private val updatedProfile = MutableLiveData<Profile>()

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val otherName = MutableLiveData<String>()
    val gender = MutableLiveData<String>()
    val workShopAddress = MutableLiveData<String>()
    val showRoomAddress = MutableLiveData<String>()
    val numberOfEmployees = MutableLiveData<Int>()
    val legalStatus = MutableLiveData<String>()
    val nameOfUnion = MutableLiveData<String>()
    val ward = MutableLiveData<String>()
    val localGovtArea = MutableLiveData<String>()
    val state = MutableLiveData<String>()

    init {
        getDefaultList()
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

    fun updateProfile(profile: Profile): LiveData<Profile> {
        updatedProfile.value = profile
        return updatedProfile
    }
}
