package com.example.happytree.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class AuthViewModel(private val authDao: AuthDao) : ViewModel() {

    fun getAuth(username: String): LiveData<Auth> {
        return authDao.getAuth(username)
    }

    suspend fun insertAuth(auth: Auth) {
        authDao.insertAuth(auth)
    }

    suspend fun countUsers(username: String): Int {
        return authDao.countUsers(username)
    }
}


