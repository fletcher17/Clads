package com.decagonhq.clads.ui.view.authenticationfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentConfirmPasswordResetBinding

class ConfirmPasswordResetFragment : Fragment() {

    private var _binding: FragmentConfirmPasswordResetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentConfirmPasswordResetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resetPasswordVerificationFragmentResetPasswordButton.setOnClickListener {
            findNavController().navigate(R.id.forgot_password_fragment)
        }
    }
}
