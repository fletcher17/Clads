package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.databinding.FragmentProfileAccountFirstNameCustomDialogBinding
import com.decagonhq.clads.databinding.FragmentProfileAccountWorkshopAddressCustomDialogBinding

class EditProfileAccountWorkshopAddressCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountWorkshopAddressCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileAccountWorkshopAddressCustomDialogBinding.inflate(
            inflater,
            container,
            false
        )

        binding.fragmentProfileAccountWorkshopAddressCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountWorkshopAddressCustomDialogOkTextView.setOnClickListener {
            val street =
                binding.fragmentProfileAccountWorkshopAddressCustomDialogStreetEditText.editText?.text.toString()
            val city =
                binding.fragmentProfileAccountWorkshopAddressCustomDialogCityEditText.editText?.text.toString()
            val state =
                binding.fragmentProfileAccountWorkshopAddressCustomDialogStateEditText.editText?.text.toString()

            Toast.makeText(context, "Workshop Address $street, $city, $state ?", Toast.LENGTH_LONG)
                .show()
            dismiss()
        }

        return binding.root
    }
}