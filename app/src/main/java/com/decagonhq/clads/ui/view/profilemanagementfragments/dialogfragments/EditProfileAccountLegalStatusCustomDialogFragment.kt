package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.databinding.FragmentProfileAccountFirstNameCustomDialogBinding
import com.decagonhq.clads.databinding.FragmentProfileAccountLegalStatusCustomDialogBinding

class EditProfileAccountLegalStatusCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountLegalStatusCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentProfileAccountLegalStatusCustomDialogBinding.inflate(inflater, container, false)

        binding.fragmentProfileAccountLegalStatusCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountLegalStatusCustomDialogOkTextView.setOnClickListener {
            val legalStatus =
                binding.fragmentProfileAccountLegalStatusCustomDialogLegalStatusEditText.editText?.text.toString()
            Toast.makeText(context, "Legal Status: $legalStatus ?", Toast.LENGTH_LONG).show()
            dismiss()
        }

        return binding.root
    }
}