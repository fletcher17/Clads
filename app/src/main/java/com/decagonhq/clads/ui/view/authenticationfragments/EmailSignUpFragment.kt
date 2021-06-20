package com.decagonhq.clads.ui.view.authenticationfragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.mappedmodel.User
import com.decagonhq.clads.databinding.FragmentEmailSignUpBinding
import com.decagonhq.clads.resource.Resource
import com.decagonhq.clads.ui.viewmodel.UserManagementViewModel
import com.decagonhq.clads.utils.validator.SignUpFormValidation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailSignUpFragment : Fragment() {

    val viewModel: UserManagementViewModel by viewModels()
    private var _binding: FragmentEmailSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmailSignUpBinding.inflate(inflater, container, false)

        //  POPULATE EMAIL SIGNUP SCREEN ACCOUNT CATEGORY SPINNER DROP DOWN LIST
        val accountCategory = resources.getStringArray(R.array.category_list)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, accountCategory)
        binding.fragmentEmailSignUpScreenAccountCategoryFilledDropdown.setAdapter(arrayAdapter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SET ONCLICK LISTENER TO THE EMAIL SIGN UP FORM SCREEN
        binding.fragmentEmailSignUpScreenSignUpButton.setOnClickListener {
            if (validateFields()) {

                val category = binding.fragmentEmailSignUpScreenAccountCategoryFilledDropdown.text.toString()
                val address = ""
                val emailAddress = binding.fragmentEmailSignUpScreenEmailAddressEditText.text.toString().trim()
                val firstName = binding.fragmentEmailSignUpScreenFirstNameEditText.text.toString().trim()
                val lastName = binding.fragmentEmailSignUpScreenLastNameEditText.text.toString().trim()
                val password = binding.fragmentEmailSignUpScreenConfirmPasswordEditText.text.toString().trim()
                val phoneNumber = getString(R.string.phone)
                val role = getString(R.string.tailor)

                val newUser = User(
                    firstName = firstName,
                    lastName = lastName,
                    email = emailAddress,
                    phoneNumber = phoneNumber,
                    category = category,
                    deliveryAddress = address,
                    role = role,
                    password = password
                )

                viewModel.registerThisUser(newUser)

                viewModel.userLiveData.observe(
                    viewLifecycleOwner,
                    {
                        when (it) {
                            is Resource.Success -> {
                                val result = it.value.payload
                                findNavController().navigate(R.id.action_email_sign_up_fragment_to_email_confirmation_fragment)
                            } else -> {
                                Toast.makeText(requireContext(), "A network error has occured", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                )
            }
        }

        // SETTING TEXT WATCHER TO THE SIGN UP FORM FIELDS FOR FIRST NAME
        binding.fragmentEmailSignUpScreenFirstNameEditText.addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    binding.fragmentEmailSignUpFirstNameLayout.error = null
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        // SETTING TEXTWATCHER TO THE SIGN UP FORM FIELDS FOR LAST NAME
        binding.fragmentEmailSignUpScreenLastNameEditText.addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    binding.fragmentEmailSignUpScreenLastNameLayout.error = null
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        // SETTING TEXTWATCHER TO THE SIGN UP FORM FIELDS FOR ACCOUNT CATEGORY SPINNER
        binding.fragmentEmailSignUpScreenAccountCategoryFilledDropdown.addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    binding.fragmentEmailSignUpScreenAccountCategoryLayout.error = null
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        // SETTING TEXTWATCHER TO THE SIGN UP FORM FIELDS FOR EMAIL ADDRESS
        binding.fragmentEmailSignUpScreenEmailAddressEditText.addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    binding.fragmentEmailSignUpScreenEmailAddressLayout.error = null
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        // SETTING TEXTWATCHER TO THE SIGN UP FORM FIELDS FOR PASSWORD
        binding.fragmentEmailSignUpScreenPasswordEditText.addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.fragmentEmailSignUpScreenPasswordLayout.error = null
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        // SETTING TEXTWATCHER TO THE SIGN UP FORM FIELDS FOR CONFIRM PASSWORD
        binding.fragmentEmailSignUpScreenConfirmPasswordEditText.addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    binding.fragmentEmailSignUpScreenConfirmPasswordLayout.error = null
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
    }

    // FUNCTION TO VALIDATE ALL INPUT TEXT FIELDS IN THE EMAIL SIGN UP SCREEN FRAGMENT
    private fun validateFields(): Boolean {
        var isFieldValidated = true
        // VALIDATING THE FIRST NAME INPUT FIELD
        if (!SignUpFormValidation.nameValidator(binding.fragmentEmailSignUpScreenFirstNameEditText.text.toString())) {
            binding.fragmentEmailSignUpFirstNameLayout.error = "Please fill first name"
            isFieldValidated = false
        } else {
            binding.fragmentEmailSignUpFirstNameLayout.error = null
        }
        // VALIDATING THE LAST NAME INPUT FIELD
        if (!SignUpFormValidation.nameValidator(binding.fragmentEmailSignUpScreenLastNameEditText.text.toString())) {
            binding.fragmentEmailSignUpScreenLastNameLayout.error = "Please fill last name"
            isFieldValidated = false
        } else {
            binding.fragmentEmailSignUpScreenLastNameLayout.error = null
        }
        // VALIDATING THE ACCOUNT CATEGORY INPUT FIELD
        if (!SignUpFormValidation.spinnerValidator(binding.fragmentEmailSignUpScreenAccountCategoryFilledDropdown.text.toString())) {
            binding.fragmentEmailSignUpScreenAccountCategoryLayout.error = "Select category"
            isFieldValidated = false
        } else {
            binding.fragmentEmailSignUpScreenAccountCategoryLayout.error = null
        }
        // VALIDATING THE EMAIL INPUT FIELD
        if (!SignUpFormValidation.emailValidator(binding.fragmentEmailSignUpScreenEmailAddressEditText.text.toString())) {
            binding.fragmentEmailSignUpScreenEmailAddressLayout.error = "Email is invalid"
            isFieldValidated = false
        } else {
            binding.fragmentEmailSignUpScreenEmailAddressLayout.error = null
        }
        // VALIDATING THE PASSWORD INPUT FIELD AND COMPARE CONFIRM PASSWORD INPUT FIELD
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

        return isFieldValidated
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
