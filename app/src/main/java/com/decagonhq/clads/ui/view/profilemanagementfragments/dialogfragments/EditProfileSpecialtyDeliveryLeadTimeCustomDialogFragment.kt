package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.databinding.FragmentProfileSpecialtyDeliveryLeadTimeCustomDialogBinding

class EditProfileSpecialtyDeliveryLeadTimeCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileSpecialtyDeliveryLeadTimeCustomDialogBinding? = null
    private val binding get() = _binding!!

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
        binding.fragmentProfileSpecialtyDeliveryLeadTimeCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileSpecialtyDeliveryLeadTimeCustomDialogOkTextView.setOnClickListener {
            val number = binding.fragmentProfileSpecialtyDeliveryLeadTimeCustomDialogDeliveryLeadTimeEditText.editText?.text
            val checkedRadioButtonId = binding.deliveryLeadTimeRadioGroup.checkedRadioButtonId

            val text = with(binding) {
                when (checkedRadioButtonId) {
                    daysRadioButton.id -> daysRadioButton.text
                    weeksRadioButton.id -> weeksRadioButton.text
                    monthsRadioButton.id -> monthsRadioButton.text
                    else -> ""
                }
            }
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
