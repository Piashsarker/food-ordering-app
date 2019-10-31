package com.mealsonwheels.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mealsonwheels.R
import com.mealsonwheels.databinding.FragmentLoginBinding
import com.mealsonwheels.ui.DashboardActivity


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

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
            val intent = Intent(activity, DashboardActivity::class.java)
            startActivity(intent)
        }


        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_signUpFragment)
        }
    }


}