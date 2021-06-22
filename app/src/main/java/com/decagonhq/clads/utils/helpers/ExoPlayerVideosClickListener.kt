package com.decagonhq.clads.utils.helpers

import com.decagonhq.clads.data.entity.mappedmodel.ResourceHomeVideoModel

interface ExoPlayerVideosClickListener {
    fun onClickVideo(resourceHomeVideoModel: ResourceHomeVideoModel)
}
