package com.evergreen.eve

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class SigninActivity : AppCompatActivity() {

    private lateinit var txtUsername: EditText
    private lateinit var txtPassword: EditText
    private lateinit var loginButton: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin) // Set the layout before accessing views

        // Initialize views
        txtUsername = findViewById(R.id.txtUsername)
        txtPassword = findViewById(R.id.txtPassword)
        loginButton = findViewById(R.id.signin)

        // Edge-to-Edge setup
        enableEdgeToEdge()

        // Login button click listener
        loginButton.setOnClickListener {
            val userName = txtUsername.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password.", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(userName, password)
                Log.d("Debug", "Username: $userName, Password: $password")
            }
        }

        // Redirect to signup
        findViewById<TextView>(R.id.redirectSignup).setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(userName: String, password: String) {
        val normalizedUserName = userName.trim() // Normalize userName for consistency

        db.collection("Auth")
            .whereEqualTo("userName", normalizedUserName)
            .get()
            .addOnSuccessListener { result ->
                if (result.isEmpty) {
                    // No matching user
                    Log.w("Login", "No user found with username: $normalizedUserName")
                    Toast.makeText(applicationContext, "Invalid username or password.", Toast.LENGTH_SHORT).show()
                } else {
                    var loginSuccess = false

                    for (document in result) {
                        val storedPassword = document.getString("password")
                        if (storedPassword == password) {
                            // Successful login
                            loginSuccess = true
                            Log.d("Login", "Login successful for user: ${document.id}")
                            Toast.makeText(applicationContext, "Login Successful!", Toast.LENGTH_SHORT).show()
                            // Proceed to the next activity or screen
                            navigateHome()
                            break
                        }
                    }

                    if (!loginSuccess) {
                        // Password mismatch
                        Log.w("Login", "Password mismatch for user: $normalizedUserName")
                        Toast.makeText(applicationContext, "Invalid username or password.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle errors during the query
                Log.e("Login", "Error querying user data", exception)
                Toast.makeText(applicationContext, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun navigateHome()
    {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}
