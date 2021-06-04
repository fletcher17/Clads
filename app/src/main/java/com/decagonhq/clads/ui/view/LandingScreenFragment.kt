package com.decagonhq.clads.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentLandingScreenBinding

class LandingScreenFragment : Fragment() {

    private var _binding: FragmentLandingScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLandingScreenBinding.inflate(inflater, container, false)
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
