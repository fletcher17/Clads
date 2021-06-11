package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.databinding.FragmentProfileSpecialtyCladsTrainedCustomDialogBinding

class EditProfileSpecialtyCladsTrainedCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileSpecialtyCladsTrainedCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileSpecialtyCladsTrainedCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yesRadioButton.setOnClickListener {
            val response = binding.yesRadioButton.text
            dismiss()
        }

        binding.noRadioButton.setOnClickListener {
            val response = binding.noRadioButton.text
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
