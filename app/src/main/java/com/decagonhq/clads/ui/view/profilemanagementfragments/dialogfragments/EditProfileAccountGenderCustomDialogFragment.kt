package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileAccountFirstNameCustomDialogBinding
import com.decagonhq.clads.databinding.FragmentProfileAccountGenderCustomDialogBinding
import com.decagonhq.clads.databinding.FragmentProfileAccountLastNameCustomDialogBinding

class EditProfileAccountGenderCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileAccountGenderCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =
            FragmentProfileAccountGenderCustomDialogBinding.inflate(inflater, container, false)

        binding.femaleRadioButton.setOnClickListener {

            val selectedGender = binding.femaleRadioButton.text.toString()
            Toast.makeText(context, "Wow! You are a $selectedGender?", Toast.LENGTH_LONG).show()
            dismiss()
        }

        binding.maleRadioButton.setOnClickListener {
            val selectedGender = binding.maleRadioButton.text.toString()
            Toast.makeText(context, "We don't do $selectedGender here!", Toast.LENGTH_LONG).show()
            dismiss()
        }

        return binding.root
    }
}