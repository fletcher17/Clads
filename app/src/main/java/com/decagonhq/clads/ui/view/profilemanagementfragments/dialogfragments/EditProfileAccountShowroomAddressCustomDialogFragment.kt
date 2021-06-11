package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.databinding.FragmentProfileAccountShowroomAddressCustomDialogBinding
import com.decagonhq.clads.viewmodel.EditProfileFragmentViewModel

class EditProfileAccountShowroomAddressCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountShowroomAddressCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

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
        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountShowroomAddressCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountShowroomAddressCustomDialogOkTextView.setOnClickListener {
            val street = binding.fragmentProfileAccountShowroomAddressCustomDialogStreetEditText.editText?.text.toString()
            val city = binding.fragmentProfileAccountShowroomAddressCustomDialogCityEditText.editText?.text.toString()
            val state = binding.fragmentProfileAccountShowroomAddressCustomDialogStateEditText.editText?.text.toString()

            viewModel.showRoomAddress.value = "$street, $city, $state"
            dismiss()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
