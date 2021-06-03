package com.decagonhq.clads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.databinding.FragmentSignUpOptionsBinding

class SignUpOptionsFragment : Fragment() {

    private var _binding: FragmentSignUpOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignUpOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentSignupOptionsSignupWithEmailButton.setOnClickListener {
            findNavController().navigate(R.id.action_sign_up_options_fragment_to_email_sign_up_fragment)
        }
        binding.fragmentSignupOptionsLoginTextTextView.setOnClickListener {
            findNavController().navigate(R.id.action_sign_up_options_fragment_to_login_fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
