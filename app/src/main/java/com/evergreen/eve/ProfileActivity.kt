package com.evergreen.eve

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    private lateinit var profilePic: Button

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            // Handle the selected image URI
            val imageView = findViewById<ImageView>(R.id.setting_profile_image)
            imageView.setImageURI(it) // Display the selected image in an ImageView
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        profilePic = findViewById(R.id.btnProPic)

        profilePic.setOnClickListener{
            showImagePicDialog()

        }
    }

    private fun showImagePicDialog()
    {
        val options = arrayOf("Camera", "Gallery")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pick Image Form")

        builder.setItems(options) {
            dialog: DialogInterface, which: Int ->
            when(which)
            {
                0 -> {
                    pickFromCamera()
                }

                1 -> {
                    pickFromGallery()
                }
            }
        }

        builder.create().show()
    }

    private fun pickFromCamera()
    {}

    private fun pickFromGallery(){

        galleryLauncher.launch("image/*")

    }
}