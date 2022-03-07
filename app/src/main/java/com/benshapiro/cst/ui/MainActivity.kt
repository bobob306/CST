package com.benshapiro.cst.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.benshapiro.cst.NavGraphDirections
import com.benshapiro.cst.R
import dagger.hilt.android.AndroidEntryPoint

// Host activity for fragments and navController
// Generate hilt component for this class
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    fun forceNavigateUp(): Boolean {
        val action = NavGraphDirections.actionGlobalLoginFragment()
        navController.navigate(action)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return forceNavigateUp() || super.onSupportNavigateUp()
    }
}