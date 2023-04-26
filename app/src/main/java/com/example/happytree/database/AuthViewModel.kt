package com.example.happytree.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AuthViewModel(private val authDao: AuthDao): ViewModel(){
    val findUser = authDao.findUser().asLiveData()


    fun addUser(auth: Auth) = viewModelScope.launch {
        authDao.addUser(auth)
    }

    fun findUser (username: String) :
            Flow<List<Auth>> = authDao.findUser(username)
}

