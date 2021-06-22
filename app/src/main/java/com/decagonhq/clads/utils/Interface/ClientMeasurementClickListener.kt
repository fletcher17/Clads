package com.decagonhq.clads.utils.Interface

import com.decagonhq.clads.utils.ClientMeasurementData

interface ClientMeasurementClickListener {
    fun onClickItem(itemName: ClientMeasurementData, position: Int)

    fun editMeasurement(measurementDetails: ClientMeasurementData, position: Int)
}
