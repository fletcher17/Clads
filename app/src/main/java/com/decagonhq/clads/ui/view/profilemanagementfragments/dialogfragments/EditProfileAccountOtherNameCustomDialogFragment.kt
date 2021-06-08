package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.databinding.FragmentProfileAccountFirstNameCustomDialogBinding
import com.decagonhq.clads.databinding.FragmentProfileAccountOtherNameCustomDialogBinding

class EditProfileAccountOtherNameCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountOtherNameCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentProfileAccountOtherNameCustomDialogBinding.inflate(inflater, container, false)

        binding.fragmentProfileAccountOtherNameCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountOtherNameCustomDialogOkTextView.setOnClickListener {
            val OtherName =
                binding.fragmentProfileAccountOtherNameCustomDialogOtherNameEditText.editText?.text.toString()
            Toast.makeText(context, "Is your Other Name really $OtherName ?", Toast.LENGTH_LONG)
                .show()
            dismiss()
        }

        return binding.root
    }
}