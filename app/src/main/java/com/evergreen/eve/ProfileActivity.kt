package com.evergreen.eve

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.net.URL

class ProfileActivity : AppCompatActivity() {

    private lateinit var profilePic: Button
    private lateinit var imageUri: Uri
    private lateinit var backBtn: ImageView
    private lateinit var userName: TextView

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            val imageView = findViewById<ImageView>(R.id.setting_profile_image)
            imageView.setImageURI(imageUri)
        }
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val imageView = findViewById<ImageView>(R.id.setting_profile_image)
            imageView.setImageURI(it)
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

        backBtn = findViewById(R.id.loadProfileBack)

        backBtn.setOnClickListener{
            intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        userName = findViewById(R.id.profileUserName)

        val receivedUserName = intent.getStringExtra("userName")
        if (!receivedUserName.isNullOrEmpty()) {
            userName.text = receivedUserName
        }

    }

    private fun showImagePicDialog()
    {
        val options = arrayOf("Camera", "Gallery")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pick Image From")

        builder.setItems(options) { _: DialogInterface, which: Int ->
            when (which) {
                0 -> pickFromCamera()
                1 -> pickFromGallery()
            }
        }
        builder.create().show()
    }


    private fun pickFromCamera()
    {
        val photoFile = createImageFile()
        imageUri = FileProvider.getUriForFile(this, "$packageName.provider", photoFile)
        cameraLauncher.launch(imageUri)
    }

    private fun pickFromGallery(){

        galleryLauncher.launch("image/*")

    }
    private fun createImageFile(): File {
        val storageDir = externalCacheDir
        return File.createTempFile("IMG_", ".jpg", storageDir)
    }
}