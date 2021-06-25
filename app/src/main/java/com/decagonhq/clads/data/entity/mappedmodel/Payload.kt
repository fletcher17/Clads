package com.decagonhq.clads.data.entity.mappedmodel

data class Payload(
    var country: String?,
    var email: String?,
    var firstName: String?,
    var gender: String?,
    var id: Int?,
    var lastName: String?,
    var phoneNumber: String?,
    var role: String?,
    var showroomAddress: ShowroomAddress?,
    var thumbnail: String?,
    var union: UnionX?,
    var workshopAddress: WorkshopAddress?
)