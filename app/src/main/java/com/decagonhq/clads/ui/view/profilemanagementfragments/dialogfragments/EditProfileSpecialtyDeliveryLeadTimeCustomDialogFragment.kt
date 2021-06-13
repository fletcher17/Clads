package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileSpecialtyDeliveryLeadTimeCustomDialogBinding
import com.decagonhq.clads.ui.viewmodel.EditProfileFragmentViewModel
import com.decagonhq.clads.utils.toast

class EditProfileSpecialtyDeliveryLeadTimeCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileSpecialtyDeliveryLeadTimeCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileSpecialtyDeliveryLeadTimeCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileSpecialtyDeliveryLeadTimeCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileSpecialtyDeliveryLeadTimeCustomDialogOkTextView.setOnClickListener {
            val number = binding.fragmentProfileSpecialtyDeliveryLeadTimeCustomDialogDeliveryLeadTimeEditText.editText?.text.toString().trim()
            val checkedRadioButtonId = binding.deliveryLeadTimeRadioGroup.checkedRadioButtonId

            val text = with(binding) {
                when (checkedRadioButtonId) {
                    daysRadioButton.id -> daysRadioButton.text
                    weeksRadioButton.id -> weeksRadioButton.text
                    monthsRadioButton.id -> monthsRadioButton.text
                    else -> ""
                }
            }

            if (number != "" && text != "") {
                viewModel.deliveryLeadTime.value = "$number $text"
                dismiss()
            } else if (number != "") {
                toast(getString(R.string.edit_profile_fragment_field_cannot_be_empty_text))
            } else if (text != "") {
                toast(getString(R.string.edit_profile_fragment_selection_cannot_be_empty_text))
            } else {
                toast(getString(R.string.edit_profile_fragment_field_and_selction_cannot_be_empty_text))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
