package com.decagonhq.clads.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads.utils.ClientAddressData
import com.decagonhq.clads.utils.ClientMeasurementData

class ClientViewModel : ViewModel() {

    val _clientAddress = MutableLiveData<ClientAddressData>()
    val clientAddress: LiveData<ClientAddressData> get() = _clientAddress

    val _clientMeasurement = MutableLiveData<ClientMeasurementData>()
    val clientMeasurement: LiveData<ClientMeasurementData> get() = _clientMeasurement

    /**client address is added */
    fun clientNewAddress(address: ClientAddressData) {
        _clientAddress.value = address
    }

    /**client measurement is added */
    fun clientNewMeasurement(measurementDetails: ClientMeasurementData) {
        _clientMeasurement.value = measurementDetails
    }
}
