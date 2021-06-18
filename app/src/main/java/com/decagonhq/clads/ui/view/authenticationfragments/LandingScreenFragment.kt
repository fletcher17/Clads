package com.decagonhq.clads.ui.view.authenticationfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentLandingScreenBinding
import com.decagonhq.clads.ui.viewmodel.UserManagementViewModel

class LandingScreenFragment : Fragment() {

    val viewModel: UserManagementViewModel by viewModels()
    private var _binding: FragmentLandingScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLandingScreenBinding.inflate(layoutInflater, container, false)
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
