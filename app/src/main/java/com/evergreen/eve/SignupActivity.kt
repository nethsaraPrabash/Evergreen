package com.evergreen.eve

import android.app.ProgressDialog
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.evergreen.eve.models.Auth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {

    private lateinit var txtEmail :EditText
    private lateinit var txtPassword :EditText
    private lateinit var txtPassword2 : EditText
    private lateinit var txtUsername :EditText
    private lateinit var btnSignUp :Button
    private lateinit var db : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        db = FirebaseFirestore.getInstance()

        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        txtUsername = findViewById(R.id.txtUsername)
        txtPassword2 = findViewById(R.id.txtPassword2)
        btnSignUp = findViewById(R.id.signup)


        btnSignUp.setOnClickListener{
            val email = txtEmail.text.toString().trim()
            val username = txtUsername.text.toString().trim()
            val password = txtPassword.text.toString()
            val password2 = txtPassword2.text.toString()


            when {
                email.isEmpty() -> {
                    txtEmail.error = "please enter email"
                }
                username.isEmpty() -> {
                    txtUsername.error = "please enter username"
                }
                password.isEmpty() -> {
                    txtPassword.error = "please enter a password"
                }
                password != password2 ->
                {
                    txtPassword.error = "Password is not matching"
                }

                else -> {
                    addDataToFirestorm(username,password,email)
                }

            }
        }
    }

    private fun addDataToFirestorm(userName: String, password: String, email: String)
    {
        val dbAuth = db.collection("Auth")

        val auth = Auth(userName,password,email)

        val progressDialog = ProgressDialog(this).apply {
            setMessage("Signing up...")
            setCancelable(false)
            show()
        }

        dbAuth.add(auth)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Your details have been added to Evergreen", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Failed to add user: $e", Toast.LENGTH_SHORT).show()
            }
    }
}