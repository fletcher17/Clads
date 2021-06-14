package com.decagonhq.clads.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClientMeasurementData(
    var nameOfMeasurement: String,
    var valueOfMeasurement: String
) : Parcelable

@Parcelize
data class ClientAddressData(
    val DeliveryAddress: String,
    val city: String,
    val state: String
) : Parcelable
