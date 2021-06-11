package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.databinding.FragmentProfileAccountLastNameCustomDialogBinding
import com.decagonhq.clads.viewmodel.EditProfileFragmentViewModel

class EditProfileAccountLastNameCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountLastNameCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentProfileAccountLastNameCustomDialogBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountLastNameCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountLastNameCustomDialogOkTextView.setOnClickListener {
            val lastName = binding.fragmentProfileAccountLastNameCustomDialogLastNameEditText.editText?.text.toString()
            viewModel.lastName.value = lastName
            dismiss()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
