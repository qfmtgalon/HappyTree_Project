package com.example.happytree.database

import kotlinx.coroutines.flow.Flow

class AuthRepository(private val authDao: AuthDao) {
    fun findUser(username: String, password: String): Flow<List<Auth>> {
        return authDao.findUser(username, password)
    }

//    fun findUsername(username: String): LiveData<List<Auth>> {
//        return authDao.findUsername(username)
//    }
}
