package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileAccountLocalGovernmentAreaCustomDialogBinding
import com.decagonhq.clads.ui.viewmodel.EditProfileFragmentViewModel
import com.decagonhq.clads.utils.toast

class EditProfileAccountLocalGovernmentAreaCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountLocalGovernmentAreaCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentProfileAccountLocalGovernmentAreaCustomDialogBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountLocalGovtAreaCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountLocalGovtAreaCustomDialogOkTextView.setOnClickListener {
            val localGovtArea = binding.fragmentProfileAccountLocalGovtAreaCustomDialogLocalGovtAreaEditText.editText?.text.toString().trim()
            if (localGovtArea != "") {
                viewModel.localGovtArea.value = localGovtArea
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
