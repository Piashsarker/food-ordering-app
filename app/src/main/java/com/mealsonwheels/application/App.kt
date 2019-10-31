package com.mealsonwheels.application

import android.app.Application
import android.util.Log

class App : Application() {
    init {
        Log.d("App.kt", "Application Class Get Called")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("App.kt", "onCreate Calling For Application")
    }
}