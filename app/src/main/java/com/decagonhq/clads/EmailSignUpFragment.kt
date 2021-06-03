package com.decagonhq.clads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.databinding.FragmentEmailSignUpBinding

class EmailSignUpFragment : Fragment() {

    private var _binding: FragmentEmailSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmailSignUpBinding.inflate(inflater, container, false)
        binding.fragmentEmailSignUpScreenSignUpButton.setOnClickListener {
            if (validateFields()) {
                // NAVIGATE TO THE EMAIL CONFIRMATION FRAGMENT

                findNavController().navigate(R.id.action_email_sign_up_fragment_to_email_confirmation_fragment)
            }
        }

        val accountCategory = resources.getStringArray(R.array.category_list)
        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, accountCategory)
        binding.fragmentEmailSignUpScreenAccountCategoryFilledDropdown.setAdapter(arrayAdapter)
        return binding.root
    }

    private fun validateFields(): Boolean {
        var isFieldValidated = true

        if (!SignUpFormValidation.nameValidator(binding.fragmentEmailSignUpScreenFirstNameEditText.text.toString())) {
            binding.fragmentEmailSignUpFirstNameLayout.error = "Please fill first name"
            isFieldValidated = false
        } else {
            binding.fragmentEmailSignUpFirstNameLayout.error = null
        }
        if (!SignUpFormValidation.passwordValidator(binding.fragmentEmailSignUpScreenPasswordEditText.text.toString())) {
            binding.fragmentEmailSignUpScreenPasswordLayout.error =
                "Password must be at least 6 characters"
            binding.fragmentEmailSignUpScreenPasswordLayout.errorIconDrawable = null
            isFieldValidated = false
        } else if (!SignUpFormValidation.comparePasswords(
                binding.fragmentEmailSignUpScreenPasswordEditText.text.toString(),
                binding.fragmentEmailSignUpScreenConfirmPasswordEditText.text.toString()
            )
        ) {
            binding.fragmentEmailSignUpScreenConfirmPasswordLayout.error = "Mismatched password"
            binding.fragmentEmailSignUpScreenConfirmPasswordLayout.errorIconDrawable = null

            isFieldValidated = false
        } else {
            binding.fragmentEmailSignUpScreenConfirmPasswordLayout.error = null
            binding.fragmentEmailSignUpScreenPasswordLayout.error = null
        }
        if (!SignUpFormValidation.nameValidator(binding.fragmentEmailSignUpScreenLastNameEditText.text.toString())) {
            binding.fragmentEmailSignUpScreenLastNameLayout.error = "Please fill last name"
            isFieldValidated = false
        } else {
            binding.fragmentEmailSignUpScreenLastNameLayout.error = null
        }
        if (!SignUpFormValidation.spinnerValidator(binding.fragmentEmailSignUpScreenAccountCategoryFilledDropdown.text.toString())) {
            binding.fragmentEmailSignUpScreenAccountCategoryLayout.error = "Select category"
            isFieldValidated = false
        } else {
            binding.fragmentEmailSignUpScreenAccountCategoryLayout.error = null
        }

        return isFieldValidated
    }
}
