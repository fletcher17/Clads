package com.decagonhq.clads

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.databinding.FragmentSignUpOptionsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class SignUpOptionsFragment : Fragment() {

    /*
    * @param cladsFirebaseAuth -> reference to firebase auth services
    * @param cladsGoogleSignInClient -> reference to Google Sign In Client
    * */
    private lateinit var cladsFirebaseAuth: FirebaseAuth
    private lateinit var cladsGoogleSignInClient: GoogleSignInClient
    var _binding: FragmentSignUpOptionsBinding? = null
    val binding get() = _binding
    val GOOGLE_SIGN_IN_REQUEST_CODE = 200

//    override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = cladsFirebaseAuth.currentUser
//      //  updateUI(currentUser)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cladsFirebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // build the layout that displays different email options
        val cladGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // display the sign in email options
        cladsGoogleSignInClient = GoogleSignIn.getClient(requireContext(), cladGoogleSignInOptions)

        //  button to initiate google sign in
//        binding.signInButton.setOnClickListener{
//            signIn()
//        }
    }

    private fun signIn() {
        // sends the selected account to the server
        val signInIntent: Intent = cladsGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.signInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {

            // task to get selected user
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // use idToken to authenticate
            account?.let { firebaseAuthWithGoogle(it) }

            firebaseAuthWithGoogle(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            updateUI(null)
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        cladsFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(
            requireActivity()
        ) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = cladsFirebaseAuth.currentUser
                Toast.makeText(requireContext(), "SignIn Successful", Toast.LENGTH_SHORT).show()
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(requireContext(), "Error Occurred", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    private fun updateUI(account: FirebaseUser?) {
        // val action = SignUpOptionsFragmentDirections.actionSignUpOptionsFragmentToEmailSignUpFragment()
        findNavController().navigate(R.id.action_sign_up_options_fragment_to_email_sign_up_fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
