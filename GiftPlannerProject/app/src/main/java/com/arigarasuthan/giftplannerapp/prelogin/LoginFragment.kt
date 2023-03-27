package com.arigarasuthan.giftplannerapp.prelogin

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.arigarasuthan.giftplannerapp.BaseFragment
import com.arigarasuthan.giftplannerapp.R
import com.arigarasuthan.giftplannerapp.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : BaseFragment() {
    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private var TAG = LoginFragment::class.java.canonicalName


    private fun initViews() {
        initGoogleSignIn()
        loginBinding.apply {
            loginwithGoogle.setOnClickListener {
                googleSignIn()
            }
            submitLogin.setOnClickListener {
                auth.signOut()
            }
            loginRegister.setOnClickListener {
                findNavController().navigate(R.id.register)
            }
            loginFpassword.setOnClickListener {
                findNavController().navigate(R.id.forgot_password)
            }
        }
    }

    private fun initGoogleSignIn() {
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("870735082696-od5hvscifl499du9h6h4l407gttdeuir.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

    }

    private fun googleSignIn() {
        showLoading("Signing In...")
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
        //startActivityForResult(signInIntent,RC_SIGN_IN)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthwithGoogle(account.idToken)
                } catch (e: ApiException) {
                    Log.d(TAG,"${e.message}")
                }
            }
        }

    private fun firebaseAuthwithGoogle(idToken: String?) {
        val creadential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(creadential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    stopLoading()
                    val user = auth.currentUser?.displayName
                    Toast.makeText(requireContext(),"Hello $user",Toast.LENGTH_LONG).show()
                } else {
                    stopLoading()
                    Log.d(TAG, "${task.exception}")
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return loginBinding.root
    }
}