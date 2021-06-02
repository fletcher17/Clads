package com.decagonhq.clads

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.decagonhq.clads.databinding.FragmentSignUpOptionsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class SignUpOptionsFragment : Fragment() {

    /*
    * @param cladsFirebaseAuth -> reference to firebase auth services
    * @param cladsGoogleSignInClient -> reference to Google Sign In Client
    * */
    private lateinit var cladsFirebaseAuth : FirebaseAuth
    private lateinit var cladsGoogleSignInClient : GoogleSignInClient
    var _binding = FragmentSignUpOptionsBinding
    val binding get() = _binding
    const val GOOGLE_SIGN_IN_REQUEST_CODE = 200




    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = cladsFirebaseAuth.currentUser
      //  updateUI(currentUser)
    }
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
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        cladsGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso);


        val signInButton = view.findViewById<Button>(R.id.button)
        signInButton.setOnClickListener{
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent: Intent = cladsGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 200) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

          //  account?.idToken?.let { firebaseAuthWithGoogle(it) }

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
          //  Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        cladsFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(requireActivity(),
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = cladsFirebaseAuth.currentUser
                        Toast.makeText(requireContext(), "SignIn Successful", Toast.LENGTH_SHORT).show()
                     //   updateUI(user)
                    } else {
                        Toast.makeText(requireContext(), "Error Occurred", Toast.LENGTH_SHORT).show()
                        // If sign in fails, display a message to the user.
//                        Snackbar.make(
//                            mBinding.mainLayout,
//                            "Authentication Failed.",
//                            Snackbar.LENGTH_SHORT
//                        ).show()
//                        updateUI(null)
                    }

                })
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        findNavController().navigate()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
