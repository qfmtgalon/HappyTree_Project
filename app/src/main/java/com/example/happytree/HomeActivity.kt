package com.example.happytree


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.happytree.LandingPage
import com.example.happytree.R
import com.example.happytree.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val drawerLayout: DrawerLayout = findViewById(R.id.homeActivityLayout)
        val navView: NavigationView = findViewById(R.id.navView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHost) as NavHostFragment
        val navController = navHostFragment.navController

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> navController.navigate(R.id.home)
                R.id.viewFragment -> navController.navigate(R.id.viewFragment)
                R.id.treeDiseases -> navController.navigate(R.id.treeDiseases)
                R.id.about -> navController.navigate(R.id.about)
                R.id.FAQs -> navController.navigate(R.id.FAQs)
                R.id.nav_logout -> {
                    // Clear the session and go back to WelcomeFragment
                    val landingPageIntent = Intent(this, LandingPage::class.java)
                    landingPageIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(landingPageIntent)
                    finish()
                }
            }
            //close the navigation drawer
            //GravityCompat.START to make sure that the drawer is closed on te start
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        bottomNavView = findViewById(R.id.bottomNavView)
        bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> navController.navigate(R.id.home)
                R.id.viewFragment -> navController.navigate(R.id.viewFragment)
                R.id.moreInfoFragment -> navController.navigate(R.id.moreInfoFragment)
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHost)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }


    override fun onBackPressed() {
        val navController = findNavController(R.id.myNavHost)
        val currentDestination = navController.currentDestination?.id

        // Check if the current destination is either updateFragment or inputFragment
        if (currentDestination == R.id.updateFragment || currentDestination == R.id.inputFragment) {
            // Navigate to the viewFragment
            navController.navigate(R.id.viewFragment)
        } else if (currentDestination != null && currentDestination != R.id.home) {
            // Navigate back to the HomeFragment
            navController.popBackStack(R.id.home, false)
        } else {
            // Exit the app and go to the Home screen
            moveTaskToBack(true)
        }
    }

}
