package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.databinding.FragmentProfileAccountGenderCustomDialogBinding
import com.decagonhq.clads.viewmodel.EditProfileFragmentViewModel

class EditProfileAccountGenderCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountGenderCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =
            FragmentProfileAccountGenderCustomDialogBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.femaleRadioButton.setOnClickListener {
            val selectedGender = binding.femaleRadioButton.text.toString()
            viewModel.gender.value = selectedGender
            dismiss()
        }

        binding.maleRadioButton.setOnClickListener {
            val selectedGender = binding.maleRadioButton.text.toString()
            viewModel.gender.value = selectedGender
            dismiss()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
