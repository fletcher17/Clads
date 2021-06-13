package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileAccountStateCustomDialogBinding
import com.decagonhq.clads.ui.viewmodel.EditProfileFragmentViewModel
import com.decagonhq.clads.utils.toast

class EditProfileAccountStateCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountStateCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentProfileAccountStateCustomDialogBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountStateCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountStateCustomDialogOkTextView.setOnClickListener {
            val state = binding.fragmentProfileAccountStateCustomDialogStateEditText.editText?.text.toString().trim()
            if (state != "") {
                viewModel.state.value = state
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
