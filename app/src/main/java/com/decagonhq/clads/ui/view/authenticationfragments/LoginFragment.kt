package com.decagonhq.clads.ui.view.authenticationfragments

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentLoginBinding
import com.decagonhq.clads.ui.view.activity.ProfileDashboardActivity
import com.decagonhq.clads.utils.GOOGLE_SIGN_IN_REQUEST_CODE
import com.decagonhq.clads.utils.validator.LoginFragmentValidation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding // profile_activity_header_view binding for this current fragment (Login fragment)
    // Creating variables to store views references
    private lateinit var showPasswordIcon: ImageView
    private lateinit var hidePasswordIcon: ImageView
    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var signInWithGoogleBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var signUpForFreeLink: TextView
    private lateinit var forgotPasswordLink: TextView
    private lateinit var cladsGoogleSignInClient: GoogleSignInClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        // Initializing the profile_activity_header_view binding variable
        binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )

        // Storing reference to views into variables
        showPasswordIcon = binding.loginScreenFragmentShowPasswordIcon
        hidePasswordIcon = binding.loginScreenFragmentHidePasswordIcon
        email = binding.loginScreenFragmentEmailAddressTextView
        password = binding.loginScreenFragmentPasswordTextView
        // Buttons
        signInWithGoogleBtn = binding.linearLayout3
        loginBtn = binding.loginScreenFragmentLoginButtonTextView
        // Text Links
        signUpForFreeLink = binding.loginScreenFragmentSignUpForFreeLinkTextView
        forgotPasswordLink = binding.loginScreenFragmentForgotPasswordTextView

        // Controlling the visibility of the password at the click of the visibility icons
        showPasswordIcon.setOnClickListener() {
            password.inputType = 1
            showPasswordIcon.visibility = View.GONE
            hidePasswordIcon.visibility = View.VISIBLE
        }

        hidePasswordIcon.setOnClickListener() {
            password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            hidePasswordIcon.visibility = View.GONE
            showPasswordIcon.visibility = View.VISIBLE
        }

        val cladGoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        // display the sign in email options
        cladsGoogleSignInClient = GoogleSignIn.getClient(requireContext(), cladGoogleSignInOptions)

        // Navigate to other screens at the buttons
        loginBtn.setOnClickListener() {
            when {
                !LoginFragmentValidation.emailValidator(email.text.toString()) -> {
                    email.error = "Invalid Email"
                }
                !LoginFragmentValidation.passwordValidator(password.text.toString()) -> {
                    password.error = "requires 6 characters or more"
                }
                else -> {
                    startActivity(Intent(requireContext(), ProfileDashboardActivity::class.java))
                }
            }
        }

        // Move to forgot password page at the click to the forgot password text
        forgotPasswordLink.setOnClickListener() {
            findNavController().navigate(R.id.reset_password_fragment)
        }

        // Move to Sign up for free at the click of th Sign up for free text
        signUpForFreeLink.setOnClickListener() {
            findNavController().navigate(R.id.action_login_fragment_to_sign_up_options_fragment)
        }

        signInWithGoogleBtn.setOnClickListener {
            googleSignIn()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun googleSignIn() {
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

            if (account != null) {
                updateUI(account)
            } else {
                findNavController().navigate(R.id.action_login_fragment_to_sign_up_options_fragment)
            }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        // pass data to dashboard with parcelable
        startActivity(Intent(requireContext(), ProfileDashboardActivity::class.java))
    }
}
