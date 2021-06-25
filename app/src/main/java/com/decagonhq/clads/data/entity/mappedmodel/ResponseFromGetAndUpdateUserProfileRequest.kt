package com.decagonhq.clads.data.entity.mappedmodel

data class ResponseFromGetAndUpdateUserProfileRequest(
    var message: String?,
    var payload: Payload?,
    var status: Int?
)