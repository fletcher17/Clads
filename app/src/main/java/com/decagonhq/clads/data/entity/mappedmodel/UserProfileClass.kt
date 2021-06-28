package com.decagonhq.clads.data.entity.mappedmodel

data class UserProfileClass(
    var country: String?,
    var email: String?,
    var firstName: String?,
    var gender: String?,
    var id: Int?,
    var lastName: String?,
    var phoneNumber: String?,
    var role: String?,
    var showroomAddress: Address?,
    var thumbnail: String?,
    var union: Union?,
    var workshopAddress: Address?
)
