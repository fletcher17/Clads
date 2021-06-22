package com.decagonhq.clads.data.entity.mappedmodel

import com.google.gson.annotations.SerializedName

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val category: String,
    @SerializedName("deliveryddress")
    val deliveryAddress: String,
    val role: String,
    val password: String,
    val gender: String? = null,
    val thumbnail: String? = null,
    val country: String? = null
)
