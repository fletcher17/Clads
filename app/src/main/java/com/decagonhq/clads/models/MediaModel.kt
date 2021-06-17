package com.decagonhq.clads.models

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class MediaModel(
    var imageUri: Int,
    var imageDescription: String,
) : Parcelable
