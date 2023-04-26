package com.example.happytree

import android.app.Application
import com.example.happytree.database.AuthDatabase

class HappyTreeApplication: Application() {
    val database: AuthDatabase by lazy { AuthDatabase.getInstance(this) }

}
