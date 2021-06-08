package com.decagonhq.clads.ui.view.cliientmanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.databinding.FragmentClientAccountTabBinding
import com.decagonhq.clads.utils.IButtonClick

class ClientAccountTabFragment : Fragment() {

    // view binding
    private var _binding: FragmentClientAccountTabBinding? = null
    private val binding get() = _binding!!

    interface IButtonClickInterface : IButtonClick

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentClientAccountTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get reference to radio group
        val radioGroup = binding.clientFragmentAccountTabRadioGroup

        // client gender variable
        var clientGender = ""

        radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            // Responds to child RadioButton checked/unchecked

            when (checkedId) {
                binding.radioButtonMale.id -> clientGender = "Male"
                binding.radioButtonFemale.id -> clientGender = "Female"
            }
        }

        // get client details for saving
        binding.addClientAccountTabNextButton.setOnClickListener {
            val clientFirstName = binding.clientAccountFragmentClientFirstNameInput.text.toString().trim()
            val clientLastName = binding.clientAccountFragmentClientLastNameInput.text.toString().trim()
            val clientPhone = binding.clientAccountFragmentClientPhoneNumberInput.text.toString().trim()
            val clientEmail = binding.clientAccountFragmentClientEmailInput.text.toString().trim()

            // create a new client object from the data
            // var client = ClientDetails(clientFirstName, clientLastName, clientPhone, clientEmail, clientGender)

            // navigate to next tab
            (parentFragment as IButtonClickInterface).buttonClick()
        }
    }
}
