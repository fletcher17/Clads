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
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountFirstNameCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountGenderCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountLastNameCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountLegalStatusCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountNumberOfEmployeesCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountOtherNameCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountShowroomAddressCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountWorkshopAddressCustomDialogFragment
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

        viewModel.firstName.observe(
            viewLifecycleOwner,
            {
                firstName ->
                binding.fragmentProfileAccountFirstNameEditText.text = firstName
            }
        )
        viewModel.lastName.observe(
            viewLifecycleOwner,
            {
                lastName ->
                binding.fragmentProfileAccountLastNameEditText.text = lastName
            }
        )
        viewModel.otherName.observe(
            viewLifecycleOwner,
            {
                otherName ->
                binding.fragmentProfileAccountOtherNameEditText.text = otherName
            }
        )
        viewModel.gender.observe(
            viewLifecycleOwner,
            {
                gender ->
                binding.fragmentProfileAccountGenderEditText.text = gender
            }
        )
        viewModel.workShopAddress.observe(
            viewLifecycleOwner,
            {
                workShopAddress ->
                binding.fragmentProfileAccountWorkshopAddressEditText.text = workShopAddress
            }
        )
        viewModel.showRoomAddress.observe(
            viewLifecycleOwner,
            {
                showRoomAddress ->
                binding.fragmentProfileAccountShowroomAddressEditText.text = showRoomAddress
            }
        )
        viewModel.numberOfEmployees.observe(
            viewLifecycleOwner,
            {
                numberOfEmployees ->
                binding.fragmentProfileAccountNumberOfEmployeesEditText.text =
                    numberOfEmployees.toString()
            }
        )
        viewModel.legalStatus.observe(
            viewLifecycleOwner,
            {
                legalStatus ->
                binding.fragmentProfileAccountLegalStatusEditText.text = legalStatus
            }
        )
        viewModel.nameOfUnion.observe(
            viewLifecycleOwner,
            {
                nameOfUnion ->
                binding.fragmentProfileAccountNameOfUnionEditText.text = (nameOfUnion as CharSequence) as Editable
            }
        )
        viewModel.ward.observe(
            viewLifecycleOwner,
            {
                ward ->
                binding.fragmentProfileAccountWardEditText.text = (ward as CharSequence) as Editable
            }
        )
        viewModel.localGovtArea.observe(
            viewLifecycleOwner,
            {
                lga ->
                binding.fragmentProfileAccountLocalGovtAreaEditText.text = (lga as CharSequence) as Editable
            }
        )
        viewModel.state.observe(
            viewLifecycleOwner,
            {
                ward ->
                binding.fragmentProfileAccountStateEditText.text = (ward as CharSequence) as Editable
            }
        )

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
