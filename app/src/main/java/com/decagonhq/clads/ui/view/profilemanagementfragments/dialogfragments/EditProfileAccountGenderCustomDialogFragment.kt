package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.databinding.FragmentProfileAccountFirstNameCustomDialogBinding
import com.decagonhq.clads.databinding.FragmentProfileAccountLastNameCustomDialogBinding

class EditProfileAccountGenderCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountLastNameCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentProfileAccountLastNameCustomDialogBinding.inflate(inflater, container, false)

        binding.fragmentProfileAccountLastNameCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountLastNameCustomDialogOkTextView.setOnClickListener {
            val lastName =
                binding.fragmentProfileAccountLastNameCustomDialogLastNameEditText.editText?.text.toString()
            Toast.makeText(context, "Is your last Name really $lastName ?", Toast.LENGTH_LONG)
                .show()
            dismiss()
        }

        return binding.root
    }
}