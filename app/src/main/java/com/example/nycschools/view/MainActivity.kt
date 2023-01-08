package com.example.nycschools.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.nycschools.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    /**
     * Activity for the flow of the application using Jetpack Navigation
     */


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Retrieve the navController from the NavHost Fragmeent
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        //Set up the action bar for use with the NavController
        setupActionBarWithNavController(navController)
    }

    /**
     * Handles the navigation when the user chooses back from the action bar
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}