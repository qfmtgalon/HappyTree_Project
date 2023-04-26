package com.example.happytree.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {


    @Query("SELECT * FROM Auth")
    fun findUser(username: String, password: String): Flow<List<Auth>>

    @Query("DELETE FROM  Auth")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(auth: Auth)

    @Query("SELECT * FROM Auth WHERE username = :username")
    fun findUser(username: String): Flow<List<Auth>>

//
//    @Query("SELECT * FROM Auth WHERE "username = :username and password = :password")
//            fun findUser(): LiveData<List<Auth>>
//
//    @Query("SELECT * FROM Auth WHERE username = :username")
//    fun findUser(username: String): Auth?
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun addUser(auth: Auth)
//
//    @Query("SELECT * FROM Auth")
//    fun findUser(username: String, password: String): LiveData<List<Auth>>
//
//    @Insert
//    suspend fun addUser(auth: Auth)
//    fun findUser(username: String): LiveData<List<Auth>>
//
//    abstract fun findUser(): LiveData<List<Auth>>


}