package com.example.happytree.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Auth(
    @PrimaryKey(autoGenerate = false)
    val username: String,
    @NonNull @ColumnInfo
    val password: String
)