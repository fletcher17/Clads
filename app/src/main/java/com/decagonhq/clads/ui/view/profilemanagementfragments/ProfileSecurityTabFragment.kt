package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.data.entity.mappedmodel.UserProfileClass
import com.decagonhq.clads.data.local.AppSharedPreference
import com.decagonhq.clads.databinding.FragmentProfileSecurityTabBinding
import com.decagonhq.clads.resource.Resource
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.PleaseWaitDialogFragment
import com.decagonhq.clads.ui.viewmodel.EditProfileFragmentViewModel
import com.decagonhq.clads.ui.viewmodel.UserManagementViewModel
import com.decagonhq.clads.utils.USER_AUTHENTICATION_PAYLOAD
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileSecurityTabFragment : Fragment() {
    private var _binding: FragmentProfileSecurityTabBinding? = null
    private val binding get() = _binding!!

    val mainViewModel: UserManagementViewModel by viewModels()

    @Inject
    lateinit var sharedPref: AppSharedPreference

    private lateinit var progressRequestingDialog: PleaseWaitDialogFragment

    private lateinit var viewModelUsedToUpdateViewsInAccountTabFragment: EditProfileFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileSecurityTabBinding.inflate(
            inflater,
            container,
            false
        )

        // Requesting Progress Bar Dialog
        progressRequestingDialog = PleaseWaitDialogFragment()

        // initializing viewModelUsedToUpdateViewsInAccountTabFragment
        viewModelUsedToUpdateViewsInAccountTabFragment = ViewModelProvider(requireActivity()).get(EditProfileFragmentViewModel::class.java)

        // setting onClickListener on the save changes button
        binding.editProfileProfileSecurityTabScreenSaveChangesBtn.setOnClickListener() {
            // Get the phone number from the phone number textView
            val phoneNumber = binding.profileSecurityTabScreenUserPhoneNumberContainerTextView.text.toString()

            viewModelUsedToUpdateViewsInAccountTabFragment.updatedProfile.observe(
                viewLifecycleOwner,
                Observer {
                    /*
                        Update what can be updated in the usersNewProfile object with the available details that we
                        have on this page, then move to the next page to update the rest
                    */
                    val incompleteNewUserProfileObject = UserProfileClass(
                        null,
                        null,
                        it.firstName,
                        it.gender,
                        null,
                        it.lastName,
                        phoneNumber,
                        null,
                        null,
                        null,
                        null,
                        null
                    )

                    // Showing the please wait dialog fragment while the network request is being made
//                    progressRequestingDialog.show(requireActivity().supportFragmentManager, "NewDialog")

//                    mainViewModel.updateUsersNewProfileObjectBeforeSendingToRemoteServer(incompleteNewUserProfileObject)
                }
            )

            /*
                sending this post requires an authentication token that we have from the backend,
                we will retrieve this from the shared preference and use it as header for this current post request
             */
            val token = sharedPref.getDataFromSharedPreference(USER_AUTHENTICATION_PAYLOAD, "string").toString()
            // After creating the update profile object, now send this to the remote server
//            mainViewModel.updateUserProfileOnRemoteServer(
//                token,
//                mainViewModel.userProfileLiveData.value!!
//            )

            // Observe the response of the update request that is saved in the mainViewModel
            mainViewModel.responseFromGetAndUpdateUserProfileRequest.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is Resource.Success<*> -> {
                            val responseFromGetAndUpdateUserProfileRequest = it.body()?.payload

                            // dismiss the please wait dailog
                            progressRequestingDialog.dismiss()

                            Toast.makeText(requireContext(), it.body()?.message, Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            // dismiss the please wait dailog
//                            progressRequestingDialog.dismiss()

                            Toast.makeText(requireContext(), "Error getting response from the backend", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
