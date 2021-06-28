package com.decagonhq.clads.data.entity.mappedmodel

data class UploadPhotoResponse(
    val message: String, // Image uploaded successfully
    val payload: ImageProfilePayload,
    val status: Int // 200
)
