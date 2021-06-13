package com.decagonhq.clads.ui.view.cliientmanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentAddDeliveryAddressBinding
import com.decagonhq.clads.utils.ClientAddressData

class AddDeliveryAddressFragment : Fragment() {

    private var _binding: FragmentAddDeliveryAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddDeliveryAddressBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        //the add delivery button, when clicked navigates to the client address tab fragment with the clients data entered in the edit text
        binding.fragmentAddDeliveryAddressButton.setOnClickListener {
            val deliveryAddress = binding.fragmentAddDeliveryEnterDeliveryEditTextInput.text.toString()
            val city = binding.fragmentAddDeliveryCityEditTextInput.text.toString()

            val clientAddress = ClientAddressData(deliveryAddress, city)

            val action = AddDeliveryAddressFragmentDirections.actionAddDeliveryAddressFragmentToClientDeliveryAdderssTabFragment(clientAddress)

            binding.fragmentAddDeliveryEnterDeliveryEditTextInput.text?.clear()
            binding.fragmentAddDeliveryCityEditTextInput.text?.clear()

            findNavController().navigate(action)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        val states = resources.getStringArray(R.array.states)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, states)

        binding.fragmentAddDeliveryStateAutoCompleteTextView.setAdapter(arrayAdapter)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
