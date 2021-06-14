package com.decagonhq.clads.ui.view.cliientmanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentClientDeliveryAdderssTabBinding
import com.decagonhq.clads.ui.viewmodel.ClientViewModel
import com.decagonhq.clads.utils.ClientAddressData

class ClientDeliveryAddressTabFragment : Fragment() {

    private var _binding: FragmentClientDeliveryAdderssTabBinding? = null
    private val binding get() = _binding!!
    lateinit var addressViewModel: ClientViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        _binding = FragmentClientDeliveryAdderssTabBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        addressViewModel = ViewModelProvider(requireParentFragment()).get(ClientViewModel::class.java)

        // Add Address button when clicked inflates the dialog
        binding.fragmentClientDeliveryAddressTabButton.setOnClickListener {
            var dialog = AlertDialog.Builder(requireContext())
            val view = LayoutInflater.from(requireContext())
                .inflate(R.layout.fragment_add_delivery_address, null)

            val states = resources.getStringArray(R.array.states)
            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, states)
            val autoCompleteStateView = view.findViewById<AutoCompleteTextView>(R.id.fragment_add_delivery_state_auto_complete_text_view)

            autoCompleteStateView.setAdapter(arrayAdapter)

            dialog.setView(view)
            val addDialog = dialog.create()
            addDialog.show()

            var enterDeliveryAddress: EditText =
                view.findViewById(R.id.fragment_add_delivery_enter_delivery_edit_text_input)
            var city: EditText =
                view.findViewById(R.id.fragment_add_delivery_city_edit_text_input)
            var addAddressbutton: Button = view.findViewById(R.id.fragment_add_delivery_address_button)

            addAddressbutton.setOnClickListener {
                val addressName = enterDeliveryAddress.text.toString()
                val cityName = city.text.toString()
                val state = autoCompleteStateView.text.toString()

                var clientAddress = ClientAddressData(addressName, cityName, state)

                addressViewModel.clientNewAddress(clientAddress)

                addDialog.dismiss()
            }
        }

        // the viewModel observes any data entered in the dialog
        addressViewModel.clientAddress.observe(
            viewLifecycleOwner,
            Observer {
                var address = binding.fragmentClientDeliveryAddressTabTextView
                val numberOfAddress =
                    binding.fragmentClientDeliveryAddressTabClientHasOneDeliveryAddressTextView
                val horizontalView = binding.fragmentClientDeliveryAddressTabHorizontalLineView

                if (it.toString() != "") {
                    address.text = "${it.DeliveryAddress}, ${it.city}, ${it.state}"

                    numberOfAddress.isVisible = true
                    horizontalView.isVisible = true
                } else {
                    numberOfAddress.isVisible = false
                    horizontalView.isVisible = false
                }
            }
        )

        return view
    }
}
