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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.mappedmodel.LoginWithGoogleCredentialsModel
import com.decagonhq.clads.data.entity.mappedmodel.UserLoginCredentials
import com.decagonhq.clads.data.local.AppSharedPreference
import com.decagonhq.clads.databinding.FragmentLoginBinding
import com.decagonhq.clads.resource.Resource
import com.decagonhq.clads.ui.view.activity.ProfileDashboardActivity
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.PleaseWaitDialogFragment
import com.decagonhq.clads.ui.viewmodel.UserManagementViewModel
import com.decagonhq.clads.utils.GOOGLE_SIGN_IN_REQUEST_CODE
import com.decagonhq.clads.utils.USER_AUTHENTICATION_PAYLOAD
import com.decagonhq.clads.utils.validator.LoginFragmentValidation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    // View model
    val viewModel: UserManagementViewModel by viewModels()

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

    // Saving the user authentication token into Shared Preference
    @Inject
    lateinit var sharedPref: AppSharedPreference

    private lateinit var progressRequestingDialog: PleaseWaitDialogFragment

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

        // Requesting Progress Bar Dialog
        progressRequestingDialog = PleaseWaitDialogFragment()

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
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        // display the sign in email options
        cladsGoogleSignInClient = GoogleSignIn.getClient(requireContext(), cladGoogleSignInOptions)

        // Navigate to other screens at the buttons
        loginBtn.setOnClickListener() {
            if (LoginFragmentValidation.emailValidator(email.text.toString())) {
                viewModel.loginThisUserViaEmail(UserLoginCredentials(email.text.toString(), password.text.toString()))

                // Showing the please wait dialog fragment while the network request is being made
                progressRequestingDialog.show(requireActivity().supportFragmentManager, "PleaseWaitDialog")

                viewModel.loginUserResponseLiveData.observe(
                    viewLifecycleOwner
                ) {
                    // closing the requesting please wait dialog fragment
                    progressRequestingDialog.dismiss()

                    val validationResponse = LoginFragmentValidation.userDetailsNetworkCallResponseValidation(it)

                    if (validationResponse.is_Successful == true) {
                        val userAuthenticationToken = validationResponse.payload

                        // Saving the token into the shared preference
                        sharedPref.saveDataToTheSharedPreference(USER_AUTHENTICATION_PAYLOAD, userAuthenticationToken!!)

                        Toast.makeText(requireContext(), "Logged in successfully", Toast.LENGTH_LONG).show()
                        // navigate to the dashboard
                        val intent = Intent(requireContext(), ProfileDashboardActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(requireContext(), "Invalid email and password combination", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                email.error = "Invalid email format"
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
        // displays the select email option
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
            val acct = completedTask.getResult(ApiException::class.java)

            val account = GoogleSignIn.getLastSignedInAccount(activity)

            if (account != null) {

                // Token gotten from google sign in authentication
                val idToken = account.idToken

                // Make new network call with the token returned by google
                viewModel.loginThisUserViaGoogle("Bearer $idToken", LoginWithGoogleCredentialsModel("Tailor"))

                // Showing the please wait dialog fragment while the network request is being made
                progressRequestingDialog.show(requireActivity().supportFragmentManager, "PleaseWaitDialog")

                // Observe the response of the server to the network call
                viewModel.loginUserWithGoogleResponseLiveData.observe(
                    viewLifecycleOwner,
                    Observer {
                        when (it) {
                            is Resource.Success -> {
                                // Dismissing the requesting please wait dialog
                                progressRequestingDialog.dismiss()
                                // save the token gotten from the server into this variable
                                val loginWithGoogleResponse = it.value.payload.toString()

                                // Save the Token to SharedPreference and move the user to the Dashboard
                                saveAuthTokenAndGoToDashBoard(loginWithGoogleResponse)
                            }
                            is Resource.Failure -> {
                                // Dismissing the requesting please wait dialog
                                progressRequestingDialog.dismiss()
                                Toast.makeText(requireContext(), "Invalid email and password combination", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                )
            } else {
                Toast.makeText(requireContext(), "Unable to login with that email", Toast.LENGTH_SHORT).show() // Will change this to baloon
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

    private fun saveAuthTokenAndGoToDashBoard(loginWithGoogleResponse: String) {
        // If there was a success and the authorization token sent back from the server (i.e the loginWithGoogleResponse is no longer empty)
        // Then navigate the user to the Dashboard
        if (loginWithGoogleResponse.isNotEmpty()) {

            // Saving the token into the shared preference
            sharedPref.saveDataToTheSharedPreference(USER_AUTHENTICATION_PAYLOAD, loginWithGoogleResponse)

            // navigate to the dashboard
            val intent = Intent(requireContext(), ProfileDashboardActivity::class.java)
            // Success message
            Toast.makeText(requireContext(), "Logged in successfully, Welcome!", Toast.LENGTH_LONG).show()
            startActivity(intent)
            // Finish the current activity
            requireActivity().finish()
        } else {
            Toast.makeText(requireContext(), "Unable to connect to the server", Toast.LENGTH_LONG).show()
        }
    }
}
