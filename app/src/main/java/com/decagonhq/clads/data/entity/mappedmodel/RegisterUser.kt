package com.decagonhq.clads.data.entity.mappedmodel

data class RegisterUser(
    val category: String,
    val country: String? = "",
    val deliveryddress: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val role: String,
    val thumbnail: String? = ""
)