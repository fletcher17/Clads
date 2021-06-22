package com.decagonhq.clads.utils.helpers

import com.decagonhq.clads.utils.ClientMeasurementData

interface ClientMeasurementClickListener {
    fun onClickItem(itemName: ClientMeasurementData)

    fun editMeasurement(measurementDetails: ClientMeasurementData)
}
