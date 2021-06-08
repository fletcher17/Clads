package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.databinding.FragmentProfileAccountFirstNameCustomDialogBinding
import com.decagonhq.clads.databinding.FragmentProfileAccountNumberOfEmployeesCustomDialogBinding

class EditProfileAccountNumberOfEmployeesCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountNumberOfEmployeesCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileAccountNumberOfEmployeesCustomDialogBinding.inflate(
            inflater,
            container,
            false
        )

        binding.fragmentProfileAccountNumberOfEmployeesCustomDialogCancelTextView.setOnClickListener {
            dismiss()
        }

        binding.fragmentProfileAccountNumberOfEmployeesCustomDialogOkTextView.setOnClickListener {
            val numberOfEmployees =
                binding.fragmentProfileAccountNumberOfEmployeesCustomDialogNumberOfEmployeesEditText.editText?.text.toString()
            Toast.makeText(
                context,
                "$numberOfEmployees!! Hmm.. Aren't you lying?",
                Toast.LENGTH_LONG
            ).show()
            dismiss()
        }

        return binding.root
    }
}