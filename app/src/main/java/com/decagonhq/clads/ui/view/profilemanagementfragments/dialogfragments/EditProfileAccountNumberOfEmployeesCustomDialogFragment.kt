package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileAccountNumberOfEmployeesCustomDialogBinding
import com.decagonhq.clads.ui.viewmodel.EditProfileFragmentViewModel
import com.decagonhq.clads.utils.toast

class EditProfileAccountNumberOfEmployeesCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountNumberOfEmployeesCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileAccountNumberOfEmployeesCustomDialogBinding.inflate(
            inflater,
            container,
            false
        )
        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountNumberOfEmployeesCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountNumberOfEmployeesCustomDialogOkTextView.setOnClickListener {
            val numberOfEmployees = binding.fragmentProfileAccountNumberOfEmployeesCustomDialogNumberOfEmployeesEditText.editText?.text.toString().trim()
            if (numberOfEmployees != "") {
                viewModel.numberOfEmployees.value = numberOfEmployees
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
