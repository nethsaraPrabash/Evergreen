package com.evergreen.eve

import android.content.Intent
import com.evergreen.eve.ProfileActivity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import com.evergreen.eve.FertilizerFragment
import com.evergreen.eve.HomeFragment
import com.evergreen.eve.LocationActivity
import com.evergreen.eve.PaymentsFragment
import com.evergreen.eve.R
import com.evergreen.eve.SettingsFragment
import com.evergreen.eve.TeaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.hdodenhof.circleimageview.CircleImageView

class HomeActivity : AppCompatActivity() {
    val homeFragment = HomeFragment()
    val fertilizerFragment = FertilizerFragment()
    val teaFragment = TeaFragment()
    val paymentsFragment = PaymentsFragment()
    val settingsFragment = SettingsFragment()
    val profileActivity = ProfileActivity()

    private lateinit var btnDropDown: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnDropDown = findViewById(R.id.dropdown_menu)

        btnDropDown.setOnClickListener {
            showPopupMenu(it as ImageView)
        }

        loadFragment(homeFragment)

        val propicClick = findViewById<CircleImageView>(R.id.profilepic)

        propicClick.setOnClickListener{
            loadProfilePage()

        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btmNavBar)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId)
            {
                R.id.home -> {
                    loadFragment(homeFragment)
                    true
                }

                R.id.fertilizer -> {
                    loadFragment(fertilizerFragment)
                    true
                }
                R.id.tea -> {
                    loadFragment(teaFragment)
                    true
                }
                R.id.payment -> {
                    loadFragment(paymentsFragment)
                    true
                }
                R.id.settings -> {
                    loadFragment(settingsFragment)
                    true
                }
                else -> false
            }
        }

        val count = intent.getLongExtra("count", 0)
        val percent = intent.getDoubleExtra("percent", 0.0)
        val total = intent.getLongExtra("total", 0)
        val userName = intent.getStringExtra("userName").toString()

        passDataToHomeFragment(count,percent,total, userName)



    }

    private fun showPopupMenu(view: ImageView) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_dropdown, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_profile -> {
                    loadProfilePage()
                    true
                }
                R.id.action_settings -> {
                    loadFragment(settingsFragment)
                    true
                }
                R.id.action_location -> {
                    getLocation()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.home_fragment_container, fragment)
        transaction.commit()
    }

    private fun getLocation()
    {
        intent = Intent(this, LocationActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun passDataToHomeFragment(count: Long, percent: Double, total: Long, userName: String) {
        val bundle = Bundle().apply {
            putLong("count", count)
            putDouble("percent", percent)
            putLong("total", total)
            putString("userName", userName)
        }

        homeFragment.arguments = bundle
        loadFragment(homeFragment)
    }

    private fun loadProfilePage() {
        val userName = intent.getStringExtra("userName").toString() // Retrieve the username
        val profileIntent = Intent(this, ProfileActivity::class.java)
        profileIntent.putExtra("userName", userName) // Pass the username as an extra
        startActivity(profileIntent)
        finish()
    }

}