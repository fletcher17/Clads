package com.decagonhq.clads.ui.view.cliientmanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentClientDeliveryAdderssTabBinding

class ClientDeliveryAddressTabFragment : Fragment() {

    private var _binding: FragmentClientDeliveryAdderssTabBinding? = null
    private val binding get() = _binding!!
    private val args: ClientDeliveryAddressTabFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentClientDeliveryAdderssTabBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val clientAddress = args.ClientAddress
        clientAddress?. let { binding.fragmentClientDeliveryAddressTabTextView.text = clientAddress.DeliveryAddress }

        //the Add Delivery Address Button navigates to the Add delivery Address Fragment
        binding.fragmentClientDeliveryAddressTabButton.setOnClickListener {
            findNavController().navigate(R.id.addDeliveryAddressFragment)
        }

        if (clientAddress != null) {
            binding.fragmentClientDeliveryAddressTabClientHasOneDeliveryAddressTextView.isVisible = true
            binding.fragmentClientDeliveryAddressTabHorizontalLineView.isVisible = true
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
