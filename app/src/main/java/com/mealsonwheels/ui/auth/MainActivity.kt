package com.mealsonwheels.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mealsonwheels.R
import com.mealsonwheels.session.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
