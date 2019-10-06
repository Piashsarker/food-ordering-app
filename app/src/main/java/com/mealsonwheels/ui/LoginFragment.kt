package com.mealsonwheels.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mealsonwheels.R


class LoginFragment : Fragment() {

    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        findViews(view)
        return view
    }

    private fun findViews(view: View) {

        btnLogin = view.findViewById(R.id.btnServerLogin)
        btnSignup = view.findViewById(R.id.btnSignup)

        btnLogin.setOnClickListener{
            val intent = Intent(activity,DashboardActivity::class.java)
            startActivity(intent)
        }


        btnSignup.setOnClickListener{
            findNavController().navigate(R.id.action_signinFragment_to_signUpFragment)
        }
    }

}