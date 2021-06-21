package com.decagonhq.clads.utils.Interface

import android.net.Uri

interface ImageClick {
    fun onImageClick(imageUri: Uri?)

    fun editImageDescription(imageDescription: String, position: Int)
}
