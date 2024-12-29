package com.evergreen.eve

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityUserSelector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_selector)

        setUpNavigation(R.id.btnFarmerLogin, SigninActivity::class.java)
        setUpNavigation(R.id.btnAdminLogin, AdminLoginActivity::class.java)
    }

    private fun <T> setUpNavigation(buttonId: Int, targetActivity: Class<T>)
    {
        findViewById<Button>(buttonId).setOnClickListener{
            intent = Intent(this, targetActivity)
            startActivity(intent)
        }
    }
}