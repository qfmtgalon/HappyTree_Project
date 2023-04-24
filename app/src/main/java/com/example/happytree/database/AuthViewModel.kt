package com.example.happytree.database

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(private val authDao: AuthDao): ViewModel(){

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    fun addUser(auth: Auth) {
        viewModelScope.launch(Dispatchers.IO) {
            authDao.addUser(auth = auth)
        }
    }

    fun userInfo(username: String, password: String) :
            Auth? = authDao.findUser(username, password)
}