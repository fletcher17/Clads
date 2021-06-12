package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.Interface.IButtonClick
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileAccountTabBinding
import com.decagonhq.clads.models.Profile
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.*
import com.decagonhq.clads.viewmodel.EditProfileFragmentViewModel

class ProfileAccountFragment : Fragment() {

    private var _binding: FragmentProfileAccountTabBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

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

        viewModel = ViewModelProvider(this).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountSaveButton.setOnClickListener {

            val firstName = viewModel.firstName.value
            val lastName = viewModel.lastName.value
            val otherName = viewModel.otherName.value
            val gender = viewModel.gender.value
            val workShopAddress = viewModel.workShopAddress.value
            val showRoomAddress = viewModel.showRoomAddress.value
            val numberOfEmployees = viewModel.numberOfEmployees.value
            val legalStatus = viewModel.legalStatus.value
            val nameOfUnion = viewModel.nameOfUnion.value
            val ward = viewModel.ward.value
            val localGovernmentArea = viewModel.localGovtArea.value
            val state = viewModel.state.value

            val profile = Profile(
                firstName, lastName, otherName, gender, workShopAddress, showRoomAddress,
                numberOfEmployees, legalStatus, nameOfUnion, ward, localGovernmentArea, state
            )

            viewModel.updateProfile(profile)

            (parentFragment as ButtonClick).buttonClicked()
        }



        binding.fragmentProfileAccountFirstNameEditText.setOnClickListener {
            val dialog = EditProfileAccountFirstNameCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_first_name_dialog_fragment_tag)
            )
        }

        viewModel.firstName.observe(
            viewLifecycleOwner,
            {
                    firstName ->
                binding.fragmentProfileAccountFirstNameEditText.text = firstName
            }
        )

        binding.fragmentProfileAccountLastNameEditText.setOnClickListener {
            val dialog = EditProfileAccountLastNameCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_last_name_dialog_fragment_tag)
            )
        }

        viewModel.lastName.observe(
            viewLifecycleOwner,
            {
                    lastName ->
                binding.fragmentProfileAccountLastNameEditText.text = lastName
            }
        )

        binding.fragmentProfileAccountOtherNameEditText.setOnClickListener {
            val dialog = EditProfileAccountOtherNameCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_other_name_dialog_fragment_tag)
            )
        }


        viewModel.otherName.observe(
            viewLifecycleOwner,
            {
                    otherName ->
                binding.fragmentProfileAccountOtherNameEditText.text = otherName
            }
        )

        binding.fragmentProfileAccountGenderEditText.setOnClickListener {
            val dialog = EditProfileAccountGenderCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_gender_dialog_fragment_tag)
            )
        }

        viewModel.gender.observe(
            viewLifecycleOwner,
            {
                    gender ->
                binding.fragmentProfileAccountGenderEditText.text = gender
            }
        )

        binding.fragmentProfileAccountShowroomAddressEditText.setOnClickListener {
            val dialog = EditProfileAccountShowroomAddressCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_showroom_address_dialog_fragment_tag)
            )
        }

        viewModel.showRoomAddress.observe(
            viewLifecycleOwner,
            {
                    showRoomAddress ->
                binding.fragmentProfileAccountShowroomAddressEditText.text = showRoomAddress
            }
        )

        binding.fragmentProfileAccountWorkshopAddressEditText.setOnClickListener {
            val dialog = EditProfileAccountWorkshopAddressCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_workshop_address_dialog_fragment_tag)
            )
        }

        viewModel.workShopAddress.observe(
            viewLifecycleOwner,
            {
                    workShopAddress ->
                binding.fragmentProfileAccountWorkshopAddressEditText.text = workShopAddress
            }
        )

        binding.fragmentProfileAccountNumberOfEmployeesEditText.setOnClickListener {
            val dialog = EditProfileAccountNumberOfEmployeesCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_number_of_employees_dialog_fragment_tag)
            )
        }

        viewModel.numberOfEmployees.observe(
            viewLifecycleOwner,
            {
                    numberOfEmployees ->
                binding.fragmentProfileAccountNumberOfEmployeesEditText.text =
                    numberOfEmployees.toString()
            }
        )

        binding.fragmentProfileAccountLegalStatusEditText.setOnClickListener {
            val dialog = EditProfileAccountLegalStatusCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_legal_status_dialog_fragment_tag)
            )
        }

        viewModel.legalStatus.observe(
            viewLifecycleOwner,
            {
                    legalStatus ->
                binding.fragmentProfileAccountLegalStatusEditText.text = legalStatus
            }
        )


        binding.fragmentProfileAccountNameOfUnionEditText.setOnClickListener {
            val dialog = EditProfileAccountNameOfUnionCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_name_of_union_dialog_fragment_tag)
            )
        }

        viewModel.nameOfUnion.observe(
            viewLifecycleOwner,
            {
                    nameOfUnion ->
                binding.fragmentProfileAccountNameOfUnionEditText.text = nameOfUnion
            }
        )

        binding.fragmentProfileAccountWardEditText.setOnClickListener {
            val dialog = EditProfileAccountWardCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_ward_dialog_fragment_tag)
            )
        }

        viewModel.ward.observe(
            viewLifecycleOwner,
            {
                    ward ->
                binding.fragmentProfileAccountWardEditText.text = ward
            }
        )

        binding.fragmentProfileAccountLocalGovtAreaEditText.setOnClickListener {
            val dialog = EditProfileAccountLocalGovernmentAreaCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_local_govt_area_dialog_fragment_tag)
            )
        }

        viewModel.localGovtArea.observe(
            viewLifecycleOwner,
            {
                    lga ->
                binding.fragmentProfileAccountLocalGovtAreaEditText.text = lga
            }
        )


        binding.fragmentProfileAccountStateEditText.setOnClickListener {
            val dialog = EditProfileAccountStateCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_state_dialog_fragment_tag)
            )
        }

        viewModel.state.observe(
            viewLifecycleOwner,
            {
                    state ->
                binding.fragmentProfileAccountStateEditText.text = state
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
