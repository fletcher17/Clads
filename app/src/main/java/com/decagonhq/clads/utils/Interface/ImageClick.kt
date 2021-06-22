package com.decagonhq.clads.utils.Interface

import android.net.Uri

interface ImageClick {
    fun onImageClick(imageUri: Uri?, description: String)
}
