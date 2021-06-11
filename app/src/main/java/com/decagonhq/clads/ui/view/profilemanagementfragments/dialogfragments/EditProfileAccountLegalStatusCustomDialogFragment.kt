package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.databinding.FragmentProfileAccountLegalStatusCustomDialogBinding
import com.decagonhq.clads.viewmodel.EditProfileFragmentViewModel

class EditProfileAccountLegalStatusCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountLegalStatusCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentProfileAccountLegalStatusCustomDialogBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountLegalStatusCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountLegalStatusCustomDialogOkTextView.setOnClickListener {
            val legalStatus = binding.fragmentProfileAccountLegalStatusCustomDialogLegalStatusEditText.editText?.text.toString()
            viewModel.legalStatus.value = legalStatus
            dismiss()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
