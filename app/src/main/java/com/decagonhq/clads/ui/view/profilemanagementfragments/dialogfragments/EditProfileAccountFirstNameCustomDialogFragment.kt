package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.databinding.FragmentProfileAccountFirstNameCustomDialogBinding

class EditProfileAccountFirstNameCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountFirstNameCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentProfileAccountFirstNameCustomDialogBinding.inflate(inflater, container, false)

        binding.fragmentProfileAccountFirstNameCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountFirstNameCustomDialogOkTextView.setOnClickListener {
            val firstName =
                binding.fragmentProfileAccountFirstNameCustomDialogFirstNameEditText.editText?.text.toString()
            Toast.makeText(context, "Is your first Name really $firstName ?", Toast.LENGTH_LONG)
                .show()
            dismiss()
        }

        return binding.root
    }
}