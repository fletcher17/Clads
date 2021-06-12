package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileAccountWorkshopAddressCustomDialogBinding
import com.decagonhq.clads.utils.toast
import com.decagonhq.clads.viewmodel.EditProfileFragmentViewModel

class EditProfileAccountWorkshopAddressCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountWorkshopAddressCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

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

        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountWorkshopAddressCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountWorkshopAddressCustomDialogOkTextView.setOnClickListener {
            val street = binding.fragmentProfileAccountWorkshopAddressCustomDialogStreetEditText.editText?.text.toString().trim()
            val city = binding.fragmentProfileAccountWorkshopAddressCustomDialogCityEditText.editText?.text.toString().trim()
            val state = binding.fragmentProfileAccountWorkshopAddressCustomDialogStateEditText.editText?.text.toString().trim()

            if (street != "" && city != "" && state != "") {
                viewModel.workShopAddress.value = "$street, $city, $state"
                dismiss()
            } else {
                toast(getString(R.string.edit_profile_fragment_field_cannot_be_empty_text))
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
