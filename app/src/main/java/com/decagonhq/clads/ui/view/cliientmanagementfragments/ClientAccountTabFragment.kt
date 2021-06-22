package com.decagonhq.clads.ui.view.cliientmanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentClientAccountTabBinding
import com.decagonhq.clads.utils.Interface.IButtonClick

class ClientAccountTabFragment : Fragment() {

    // view binding
    private var _binding: FragmentClientAccountTabBinding? = null
    private val binding get() = _binding!!
    private var clientGender: String? = null

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

        radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            // Responds to child RadioButton checked/unchecked

            when (checkedId) {
                binding.radioButtonMale.id -> clientGender = getString(R.string.gender_male)
                binding.radioButtonFemale.id -> clientGender = getString(R.string.gender_female)
            }
        }

        // get client details for saving
        binding.addClientAccountTabNextButton.setOnClickListener {
            // navigate to next tab
            (parentFragment as IButtonClickInterface).buttonClicked()
        }
    }
}
