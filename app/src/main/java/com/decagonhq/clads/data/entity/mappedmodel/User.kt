package com.decagonhq.clads.data.entity.mappedmodel

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val category: String,
    val deliveryddress: String,
    val role: String,
    val password: String,
    val gender: String? = null,
    val thumbnail: String? = null,
    val country: String? = null
)
