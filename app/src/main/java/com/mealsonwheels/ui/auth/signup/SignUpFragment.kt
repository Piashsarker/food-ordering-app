package com.mealsonwheels.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mealsonwheels.Constants
import com.mealsonwheels.R
import com.mealsonwheels.databinding.FragmentSignUpBinding
import com.mealsonwheels.session.SessionManager
import com.mealsonwheels.ui.DashboardActivity
import com.mealsonwheels.utils.ValidationUtil


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sessionManager: SessionManager
    private lateinit var db: FirebaseFirestore
    private val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        sessionManager = SessionManager(activity!!.applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        attachListener()
        return binding.root
    }

    private fun attachListener() {
        binding.btnSignup.setOnClickListener {

            val fullName = binding.etFullName.text.toString()
            val mobile = binding.etMobile.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPass = binding.etConfirmPass.text.toString()

            if (ValidationUtil.isValidUsername(activity!!.applicationContext!!, fullName) &&
                ValidationUtil.isValidMobile(activity!!.applicationContext!!, mobile) &&
                ValidationUtil.isValidEmail(activity!!.applicationContext!!, email) &&
                ValidationUtil.isValidPassword(activity!!.applicationContext!!, password) &&
                ValidationUtil.isValidPassword(activity!!.applicationContext!!, confirmPass) &&
                (password == confirmPass)
            ) {
                signupForFirebase(
                    email, password, fullName, mobile
                )
            }

        }
    }

    private fun signupForFirebase(
        email: String,
        password: String,
        fullName: String,
        mobile: String
    ) {

        binding.progressSignup.show()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = auth.currentUser
                sessionManager.createLoginSession(password, email, user!!.uid)
                saveUserInfoInDatabase(user.uid, email, fullName, mobile)
            } else {
                binding.progressSignup.show()
                Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUserInfoInDatabase(
        uid: String,
        email: String,
        fullName: String,
        mobile: String
    ) {
        val user = hashMapOf(
            Constants.UID to uid,
            Constants.EMAIL to email,
            Constants.FULL_NAME to fullName,
            Constants.MOBILE to mobile
        )
        db.collection(Constants.USERS)
            .add(user)
            .addOnSuccessListener { documentReference ->
                binding.progressSignup.show()
                gotoMainActivity()
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                binding.progressSignup.show()
                Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun gotoMainActivity() {
        val intent = Intent(activity, DashboardActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }


}
