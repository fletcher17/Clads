package com.decagonhq.clads.data.entity.mappedmodel

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val gender: String,
    val category: String,
    val deliveryddress: String,
    val thumbnail: String? = "",
    val country: String? = "",
    val role: String,
    val password: String,
    )
