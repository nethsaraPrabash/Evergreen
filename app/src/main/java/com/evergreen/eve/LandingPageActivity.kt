package com.evergreen.eve

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LandingPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing_page)

        setupNavigation(R.id.signinPage, SigninActivity::class.java)
        setupNavigation(R.id.signupPage, SignupActivity::class.java)
    }

    private fun <T> setupNavigation(buttonId: Int, targetActivity: Class<T>)
    {
        findViewById<Button>(buttonId).setOnClickListener{
            startActivity(Intent(this,targetActivity))
        }
    }
}