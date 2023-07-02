package com.example.happytree

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


import androidx.navigation.Navigation

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navController = Navigation.findNavController(this, R.id.myNavHostFragment)
        navController.navigate(R.id.homeActivity)
    }

    fun logout(item: MenuItem) {

        // Handle logout action here
    }

}

