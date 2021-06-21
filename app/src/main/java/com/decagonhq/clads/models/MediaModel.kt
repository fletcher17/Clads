package com.decagonhq.clads.models

import android.net.Uri
import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class MediaModel(
    var imageUri: Uri?,
    var imageDescription: String,
) : Parcelable
