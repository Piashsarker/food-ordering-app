package com.mealsonwheels.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mealsonwheels.R
import com.mealsonwheels.databinding.FragmentLoginBinding
import com.mealsonwheels.session.SessionManager
import com.mealsonwheels.ui.DashboardActivity
import com.mealsonwheels.utils.ValidationUtil


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var sessionManager: SessionManager

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(activity!!.applicationContext)
        auth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        attachButtonListener()
        return binding.root
    }

    private fun attachButtonListener() {

        binding.btnServerLogin.setOnClickListener {
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()

            if (ValidationUtil.isValidEmail(activity!!.applicationContext, email) &&
                ValidationUtil.isValidPassword(activity!!.applicationContext, password)
            ) {
                binding.addressLookingUp.show()
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    binding.addressLookingUp.hide()
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        sessionManager.createLoginSession(password, email, user!!.uid)
                        gotoDashboard()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }

        }


        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_signUpFragment)
        }

    }

    private fun gotoDashboard() {
        val intent = Intent(activity, DashboardActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }


}