package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.Interface.IButtonClick
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileAccountTabBinding
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.*


class ProfileAccountFragment : Fragment() {

    private var _binding: FragmentProfileAccountTabBinding? = null
    private val binding get() = _binding!!

    interface ButtonClick : IButtonClick

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileAccountTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentProfileAccountSaveButton.setOnClickListener {
            (parentFragment as ButtonClick).buttonClicked()
        }


        binding.fragmentProfileAccountFirstNameEditText.setOnClickListener {
            val dialog = EditProfileAccountFirstNameCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_first_name_dialog_fragment_tag)
            )
        }

        binding.fragmentProfileAccountLastNameEditText.setOnClickListener {
            val dialog = EditProfileAccountLastNameCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_last_name_dialog_fragment_tag)
            )
        }

        binding.fragmentProfileAccountOtherNameEditText.setOnClickListener {
            val dialog = EditProfileAccountOtherNameCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_other_name_dialog_fragment_tag)
            )
        }

        binding.fragmentProfileAccountShowroomAddressEditText.setOnClickListener {
            val dialog = EditProfileAccountShowroomAddressCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_showroom_address_dialog_fragment_tag)
            )
        }

        binding.fragmentProfileAccountWorkshopAddressEditText.setOnClickListener {
            val dialog = EditProfileAccountWorkshopAddressCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_workshop_address_dialog_fragment_tag)
            )
        }

        binding.fragmentProfileAccountNumberOfEmployeesEditText.setOnClickListener {
            val dialog = EditProfileAccountNumberOfEmployeesCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_number_of_employees_dialog_fragment_tag)
            )
        }

        binding.fragmentProfileAccountLegalStatusEditText.setOnClickListener {
            val dialog = EditProfileAccountLegalStatusCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_legal_status_dialog_fragment_tag)
            )
        }

        binding.fragmentProfileAccountGenderEditText.setOnClickListener {
            val dialog = EditProfileAccountGenderCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_gender_dialog_fragment_tag)
            )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
