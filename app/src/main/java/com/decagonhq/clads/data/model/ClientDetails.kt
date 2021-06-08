package com.decagonhq.clads.data.model

data class ClientDetails(
    var firstName: String? = null,
    var lastName: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var gender: String? = null,
    var address: String? = null,
    var measurement: MutableList<MutableMap<String, String>>
)
