package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileAccountOtherNameCustomDialogBinding
import com.decagonhq.clads.utils.toast
import com.decagonhq.clads.viewmodel.EditProfileFragmentViewModel

class EditProfileAccountOtherNameCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountOtherNameCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentProfileAccountOtherNameCustomDialogBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountOtherNameCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountOtherNameCustomDialogOkTextView.setOnClickListener {
            val otherName = binding.fragmentProfileAccountOtherNameCustomDialogOtherNameEditText.editText?.text.toString().trim()
            if (otherName != "") {
                viewModel.otherName.value = otherName
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
