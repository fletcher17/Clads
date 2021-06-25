package com.decagonhq.clads.ui.view.authenticationfragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.data.local.AppSharedPreference
import com.decagonhq.clads.databinding.FragmentLandingScreenBinding
import com.decagonhq.clads.ui.view.activity.ProfileDashboardActivity
import com.decagonhq.clads.ui.viewmodel.UserManagementViewModel
import com.decagonhq.clads.utils.USER_AUTHENTICATION_PAYLOAD
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LandingScreenFragment : Fragment() {

    val viewModel: UserManagementViewModel by viewModels()

    // Saving the user authentication token into Shared Preference
    @Inject
    lateinit var sharedPref: AppSharedPreference

    private var _binding: FragmentLandingScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLandingScreenBinding.inflate(layoutInflater, container, false)

        // Check if the user already have authentication token stored in shared preference
        // If true, then the user had been authenticated before, 
        // then there is no need for the user to go through the login page again hence just redirect the user to the Dashboard

        val storedAuth = sharedPref.getDataFromSharedPreference(USER_AUTHENTICATION_PAYLOAD, "string")

        if ((storedAuth is String) && storedAuth.isNotEmpty()) {
            Toast.makeText(requireContext(), "Welcome back!", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), ProfileDashboardActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentLandingScreenSignUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_landing_screen_fragment_to_sign_up_options_fragment)
        }

        binding.fragmentLandingScreenLoginButton.setOnClickListener {

            findNavController().navigate(R.id.action_landing_screen_fragment_to_login_fragment)
        }
    }
}
