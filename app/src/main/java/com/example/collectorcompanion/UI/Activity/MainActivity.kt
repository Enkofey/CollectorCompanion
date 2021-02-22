package com.example.collectorcompanion.UI.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.collectorcompanion.R
import com.example.collectorcompanion.UI.Fragment.CollectionFragment
import com.example.collectorcompanion.UI.Fragment.ProfilFragment
import com.example.collectorcompanion.UI.Fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.menuNavigationBar)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(
        R.id.fragment_container, CollectionFragment()).commit()
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_collection -> selectedFragment = CollectionFragment()
            R.id.nav_search -> selectedFragment = SearchFragment()
            R.id.nav_profile -> selectedFragment = ProfilFragment()
        }
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
            .replace(R.id.fragment_container, selectedFragment!!).commit()
        true
    }
}