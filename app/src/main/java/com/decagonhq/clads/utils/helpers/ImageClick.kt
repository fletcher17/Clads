package com.decagonhq.clads.utils.helpers

import android.net.Uri

interface ImageClick {
    fun onImageClick(imageUri: Uri?, description: String)
}
