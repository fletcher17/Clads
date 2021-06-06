package com.decagonhq.clads.ui.view.authenticationfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentEmailConfirmationBinding

class EmailConfirmationFragment : Fragment() {

    private var _binding: FragmentEmailConfirmationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentEmailConfirmationBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        // the verification button which navigates to the login fragment when clicked
        binding.fragmentEmailConfirmationEmailVerificationButton.setOnClickListener {
            findNavController().navigate(R.id.login_fragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
