package com.evergreen.eve

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.evergreen.eve.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var myMap: GoogleMap
    private lateinit var backBtn: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_location)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        backBtn =  findViewById(R.id.loadLocationBack)

        backBtn.setOnClickListener{
            intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)

        }
    }




    override fun onMapReady(googleMap: GoogleMap) {

        myMap = googleMap
        // Add a marker at Galle and move the camera
        val galle = LatLng(6.1837, 80.2782)
        myMap.addMarker(
            MarkerOptions()
                .position(galle)
                .title("Galle")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)) // Blue marker
        )
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(galle, 15f)) // Zoom level set to 15

        // Set map type (default is NORMAL)
        myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL)


    }
}