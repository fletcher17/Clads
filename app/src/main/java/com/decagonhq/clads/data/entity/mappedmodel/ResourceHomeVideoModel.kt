package com.decagonhq.clads.data.entity.mappedmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResourceHomeVideoModel(
    var videoUrl: String,
    var thumbNailUrl: String,
    var videoTitle: String
) : Parcelable
