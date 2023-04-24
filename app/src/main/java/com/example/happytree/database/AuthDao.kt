package com.example.happytree.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AuthDao {
    @Query("SELECT * FROM Auth WHERE username = :username and password = :password")
    fun findUser(username: String, password: String): Auth?

    @Query("SELECT * FROM Auth WHERE username = :username")
    fun findUser(username: String): Auth?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(auth: Auth)
}