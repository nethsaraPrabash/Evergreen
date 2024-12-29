package com.evergreen.eve

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var emailTextView: EditText
    private lateinit var passwordTextView: EditText
    private lateinit var loginButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_login)

        mAuth = FirebaseAuth.getInstance();

        emailTextView = findViewById(R.id.txtAdminEmail)
        passwordTextView = findViewById(R.id.txtAdminPassword)
        loginButton = findViewById(R.id.btnAdminLogin)


        loginButton.setOnClickListener{
            loginAccount()
        }
    }

    private fun loginAccount()
    {
        val email = emailTextView.text.toString()
        val password = passwordTextView.text.toString()

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(applicationContext, "Please Enter Email", Toast.LENGTH_LONG).show()
            return
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(applicationContext, "Please Enter password", Toast.LENGTH_LONG).show()
            return
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            task -> if (task.isSuccessful){
                Toast.makeText(applicationContext, "Login successful!!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else
        {
            Toast.makeText(applicationContext, "Login failed!", Toast.LENGTH_LONG).show()
        }
        }
    }
}