package com.example.collectorcompanion.UI.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collectorcompanion.Model.Application.GlobalApplication
import com.example.collectorcompanion.R
import com.example.collectorcompanion.UI.Adapter.CollectionAdapter
import com.example.collectorcompanion.UI.Fragment.CollectionFragment
import com.example.collectorcompanion.UIModel.ApplicationViewModel
import com.example.collectorcompanion.UIModel.ApplicationViewModelFactory
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
            //R.id.nav_search -> selectedFragment = EpisodeFragment()
            //R.id.nav_account -> selectedFragment = AccountFragment()
        }
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
            .replace(R.id.fragment_container, selectedFragment!!).commit()
        true
    }
}