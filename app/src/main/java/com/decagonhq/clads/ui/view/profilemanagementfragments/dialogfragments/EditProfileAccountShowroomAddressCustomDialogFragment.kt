package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.databinding.FragmentProfileAccountFirstNameCustomDialogBinding
import com.decagonhq.clads.databinding.FragmentProfileAccountShowroomAddressCustomDialogBinding

class EditProfileAccountShowroomAddressCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountShowroomAddressCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileAccountShowroomAddressCustomDialogBinding.inflate(
            inflater,
            container,
            false
        )

        binding.fragmentProfileAccountShowroomAddressCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountShowroomAddressCustomDialogOkTextView.setOnClickListener {
            val street =
                binding.fragmentProfileAccountShowroomAddressCustomDialogStreetEditText.editText?.text.toString()
            val city =
                binding.fragmentProfileAccountShowroomAddressCustomDialogCityEditText.editText?.text.toString()
            val state =
                binding.fragmentProfileAccountShowroomAddressCustomDialogStateEditText.editText?.text.toString()

            Toast.makeText(context, "Showroom Address: $street, $city, $state", Toast.LENGTH_LONG)
                .show()
            dismiss()
        }

        return binding.root
    }
}