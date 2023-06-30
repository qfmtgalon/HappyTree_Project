package com.example.happytree

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.happytree.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHost) as NavHostFragment
        val navController = navHostFragment.navController

        binding.navBottom.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.viewFragment -> {
                    navController.navigate(R.id.viewFragment)
                    true
                }
                R.id.home -> {
                    navController.navigate(R.id.home)
                    true
                }
//                R.id.careFragment -> {
//                    navController.navigate(R.id.careFragment)
//                    true
//                }
                else -> false
            }
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.home)
                    true
                }
                R.id.viewFragment -> {
                    navController.navigate(R.id.viewFragment)
                    true
                }
                R.id.treeDiseases -> {
                    navController.navigate(R.id.treeDiseases)
                    true
                }
                R.id.about -> {
                    navController.navigate(R.id.about)
                    true
                }
                R.id.FAQs -> {
                    navController.navigate(R.id.FAQs)
                    true
                }
                else -> false
            }
        }



        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupWithNavController(binding.navBottom, navController)
    }


    override fun onBackPressed() {
        val navController = findNavController(R.id.myNavHost)
        val currentDestination = navController.currentDestination?.id

        // Check if the current destination is the WelcomeFragment
        if (currentDestination == R.id.welcome) {
            // Exit the app and go to the Home screen
            moveTaskToBack(true)
        } else if (supportFragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
        } else {
            moveTaskToBack(true)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHost)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    fun logout(menuItem: MenuItem) {
        val intent = Intent(this, LandingPage::class.java)
        startActivity(intent)
        finish()
    }

}