package com.decagonhq.clads.data.entity.mappedmodel

data class ImageProfilePayload(
    val downloadUri: String, // https://darot-image-upload-service.herokuapp.com/api/v1/download/d264e72c-2fd3-4a3c-a3b7-9526a29e0cdf
    val fileId: String, // d264e72c-2fd3-4a3c-a3b7-9526a29e0cdf
    val fileName: String, // addtaskicon.png
    val fileType: String, // image/png
    val uploadStatus: Boolean // true
)
