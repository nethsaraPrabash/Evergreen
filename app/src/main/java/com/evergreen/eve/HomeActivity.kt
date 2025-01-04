package com.evergreen.eve

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val count = intent.getLongExtra("count", 0)
        val percent = intent.getDoubleExtra("percent", 0.0)
        val total = intent.getLongExtra("total", 0)
        val userName = intent.getStringExtra("userName").toString()

        getHome(count, percent, total, userName)

    }

    private fun getHome(count: Long, percent: Double, total: Long, userName: String)
    {
        val bundle =Bundle().apply {
            putLong("count", count)
            putDouble("percent", percent)
            putLong("total", total)
            putString("userName", userName)
        }

        val fragment = HomeFragment().apply {
            arguments = bundle
        }


        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.home_fragment_container, fragment)
        fragmentTransaction.commit()
    }
}