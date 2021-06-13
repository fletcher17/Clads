package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileAccountGenderCustomDialogBinding
import com.decagonhq.clads.ui.viewmodel.EditProfileFragmentViewModel
import com.decagonhq.clads.utils.toast

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountGenderCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountGenderCustomDialogOkTextView.setOnClickListener {
            val checkedRadioButtonId = binding.genderRadioGroup.checkedRadioButtonId

            val text = with(binding) {
                when (checkedRadioButtonId) {
                    femaleRadioButton.id -> femaleRadioButton.text
                    maleRadioButton.id -> maleRadioButton.text
                    else -> ""
                }
            }

            if (text != "") {
                viewModel.gender.value = "$text"
                dismiss()
            } else {
                toast(getString(R.string.edit_profile_fragment_selection_cannot_be_empty_text))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
