package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.utils.APP_ACTIVITY
import com.example.notes.utils.Preferences

class MainActivity : AppCompatActivity() {


    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP_ACTIVITY = this

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        mToolbar = binding.toolbar
        setSupportActionBar(mToolbar)
        title = "Notes"

        Preferences.getPreferences(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}