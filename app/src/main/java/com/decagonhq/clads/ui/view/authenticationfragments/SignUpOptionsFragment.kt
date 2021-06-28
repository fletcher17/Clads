package com.decagonhq.clads.ui.view.authenticationfragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentSignUpOptionsBinding
import com.decagonhq.clads.utils.GOOGLE_SIGN_IN_REQUEST_CODE
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class SignUpOptionsFragment : Fragment() {

    /*
    * @param cladsFirebaseAuth -> reference to firebase auth services
    * @param cladsGoogleSignInClient -> reference to Google Sign In Client
    * */

    private lateinit var cladsGoogleSignInClient: GoogleSignInClient
    private var _binding: FragmentSignUpOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentSignUpOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // build the layout that displays different email options
        val cladGoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        // display the sign in email options
        cladsGoogleSignInClient = GoogleSignIn.getClient(requireContext(), cladGoogleSignInOptions)

        binding.fragmentSignupOptionsSignupWithGoogleButton.setOnClickListener {
            googleSignIn()
        }

        binding.fragmentSignupOptionsSignupWithEmailButton.setOnClickListener {
            findNavController().navigate(R.id.action_sign_up_options_fragment_to_email_sign_up_fragment)
        }

        binding.fragmentSignupOptionsLoginTextTextView.setOnClickListener {
            findNavController().navigate(R.id.action_sign_up_options_fragment_to_login_fragment)
        }
    }

    private fun googleSignIn() {
        cladsGoogleSignInClient.signOut()
        // displays the select email options
        val signInIntent: Intent = cladsGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.signInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {

            // task to get selected email
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // use idToken to authenticate
            account?.let { updateUI(it) }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        val action = SignUpOptionsFragmentDirections.actionSignUpOptionsFragmentToEmailSignUpFragment()
        findNavController().navigate(R.id.action_sign_up_options_fragment_to_email_sign_up_fragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // reset binding to null
        _binding = null
    }
}
