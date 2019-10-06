package com.mealsonwheels.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mealsonwheels.R


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                    val intent = Intent(baseContext,MainActivity::class.java);
                    startActivity(intent)

                }catch (e:Exception)
                {
                    e.printStackTrace()
                }
            }
        }

        background.start()

    }

}
