package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.Profile
import com.decagonhq.clads.data.entity.mappedmodel.Address
import com.decagonhq.clads.data.entity.mappedmodel.Union
import com.decagonhq.clads.data.entity.mappedmodel.UserProfileClass
import com.decagonhq.clads.databinding.FragmentProfileAccountTabBinding
import com.decagonhq.clads.resource.Resource
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountFirstNameCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountGenderCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountLastNameCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountLegalStatusCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountLocalGovernmentAreaCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountNameOfUnionCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountNumberOfEmployeesCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountOtherNameCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountShowroomAddressCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountStateCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountWardCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileAccountWorkshopAddressCustomDialogFragment
import com.decagonhq.clads.ui.viewmodel.EditProfileFragmentViewModel
import com.decagonhq.clads.ui.viewmodel.UserManagementViewModel
import com.decagonhq.clads.utils.helpers.IButtonClick
import com.google.android.material.snackbar.Snackbar
import com.theartofdev.edmodo.cropper.CropImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileAccountFragment : Fragment() {

    /* 
        UserManagementViewModel, the main viewModel for the app, the other view models reference in this page
        are just to get data from the dialog fragment to the respective textViews 
    */
    val userManagementViewModel: UserManagementViewModel by viewModels()

    // INITIALIZING CROP IMAGE LAUNCHER
    private val cropActivityResultContract = object : ActivityResultContract<Any?, Uri>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return CropImage.activity()
                .setCropMenuCropButtonTitle("Done")
                .setAspectRatio(1, 1)
                .getIntent(context)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            var imageUri: Uri? = null
            try {
                imageUri = CropImage.getActivityResult(intent).uri
            } catch (e: Exception) {
            }

            return imageUri
        }
    }

    // INITIALIZING CROP IMAGE LAUNCHER
    private lateinit var cropActivityResultLauncher: ActivityResultLauncher<Any?>
    private var _binding: FragmentProfileAccountTabBinding? = null
    private val binding get() = _binding!!
    private lateinit var editProfileViewModel: EditProfileFragmentViewModel

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

        // SETTING THE CROP IMAGE LAUNCHER TO THE PROFILE IMAGE
        cropActivityResultLauncher = registerForActivityResult(cropActivityResultContract) {

            it?.let { uri ->
                binding.fragmentProfileAccountProfileImageView.setImageURI(uri)
            }
        }

        editProfileViewModel = ViewModelProvider(requireActivity()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileAccountSaveButton.setOnClickListener {

            val firstName = editProfileViewModel.firstName.value
            val lastName = editProfileViewModel.lastName.value
            val otherName = editProfileViewModel.otherName.value
            val gender = editProfileViewModel.gender.value
            val workShopAddress = editProfileViewModel.workShopAddress.value
            val showRoomAddress = editProfileViewModel.showRoomAddress.value
            val numberOfEmployees = editProfileViewModel.numberOfEmployees.value
            val legalStatus = editProfileViewModel.legalStatus.value
            val nameOfUnion = editProfileViewModel.nameOfUnion.value
            val ward = editProfileViewModel.ward.value
            val localGovernmentArea = editProfileViewModel.localGovtArea.value
            val state = editProfileViewModel.state.value

            val profile = Profile(
                firstName, lastName, otherName, gender, workShopAddress, showRoomAddress,
                numberOfEmployees, legalStatus, nameOfUnion, ward, localGovernmentArea, state
            )

            editProfileViewModel.updateProfile(profile)
            editProfileViewModel.updatedProfile.observe(
                viewLifecycleOwner,
                Observer {
                    /*
                Update what can be updated in the usersNewProfile object with the available details that we
                have on this page, then move to the next page to update the rest
            */
                    val incompleteNewUserProfileObject = UserProfileClass(
                        "",
                        "",
                        it.firstName,
                        it.gender,
                        0,
                        it.lastName,
                        "",
                        "",
                        createAddressObject(it.showRoomAddress),
                        "",
                        createUnionObject(it.localGovernmentArea, it.nameOfUnion, it.state, it.ward),
                        createAddressObject(it.workShopAddress)
                    )

//                    mainViewModel.updateUsersNewProfileObjectBeforeSendingToRemoteServer(
//                        incompleteNewUserProfileObject
//                    )
                }
            )

            (parentFragment as ButtonClick).buttonClicked()
        }
        // SETTING ONCLICKLISTENER TO THE TAP TO CHANGE TEXT
        binding.fragmentProfileAccountTapToChangeTextView.setOnClickListener {
            cropActivityResultLauncher.launch(null)
        }

        binding.fragmentProfileAccountFirstNameEditText.setOnClickListener {
            val dialog = EditProfileAccountFirstNameCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_account_fragment_first_name_dialog_fragment_tag)
            )
        }

        editProfileViewModel.firstName.observe(
            viewLifecycleOwner,
            { firstName ->
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

        editProfileViewModel.lastName.observe(
            viewLifecycleOwner,
            { lastName ->
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

        editProfileViewModel.otherName.observe(
            viewLifecycleOwner,
            { otherName ->
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

        editProfileViewModel.gender.observe(
            viewLifecycleOwner,
            { gender ->
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

        editProfileViewModel.showRoomAddress.observe(
            viewLifecycleOwner,
            { showRoomAddress ->
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

        editProfileViewModel.workShopAddress.observe(
            viewLifecycleOwner,
            { workShopAddress ->
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

        editProfileViewModel.numberOfEmployees.observe(
            viewLifecycleOwner,
            { numberOfEmployees ->
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

        editProfileViewModel.legalStatus.observe(
            viewLifecycleOwner,
            { legalStatus ->
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

        editProfileViewModel.nameOfUnion.observe(
            viewLifecycleOwner,
            { nameOfUnion ->
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

        editProfileViewModel.ward.observe(
            viewLifecycleOwner,
            { ward ->
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

        editProfileViewModel.localGovtArea.observe(
            viewLifecycleOwner,
            { lga ->
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

        editProfileViewModel.state.observe(
            viewLifecycleOwner,
            { state ->
                binding.fragmentProfileAccountStateEditText.text = state
            }
        )

        userManagementViewModel.clientProfileLiveData.observe(
            viewLifecycleOwner, Observer {
               when(it) {
                   is Resource.Success -> {
                       val result = it.value.payload
                       binding.fragmentProfileAccountFirstNameEditText.text = result.firstName
                       binding.fragmentProfileAccountLastNameEditText.text = result.lastName
                       binding.fragmentProfileAccountGenderEditText.text = result.gender
                       binding.fragmentProfileAccountWorkshopAddressEditText.text = result.workshopAddress.toString()
                       binding.fragmentProfileAccountShowroomAddressEditText.text = result.showroomAddress.toString()
                       binding.fragmentProfileAccountNameOfUnionEditText.text = result.union.name
                       binding.fragmentProfileAccountWardEditText.text = result.union.ward
                       binding.fragmentProfileAccountLocalGovtAreaEditText.text = result.union.lga
                       binding.fragmentProfileAccountStateEditText.text = result.union.state
                   }

                   is Resource.Failure -> {
                       Toast.makeText(requireContext(), "Invalid username/password", Toast.LENGTH_LONG).show()
                   }
               }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun createAddressObject(address: String?): Address? {
        val splittedAddress = address?.split(",")
        val street = splittedAddress?.get(0)?.trim()
        val city = splittedAddress?.get(1)?.trim()
        val state = splittedAddress?.last()?.trim()

        return Address(city, state, street)
    }

    private fun createUnionObject(lga: String?, name: String?, state: String?, ward: String?): Union? {
        return Union(lga, name, state, ward)
    }
}
