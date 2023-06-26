package com.example.happytree.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FarmViewModel(application: Application): AndroidViewModel(application){
    private val repository: FarmRepository
    init {
        val farmDao=FarmDatabase.getDatabase(application).FarmDao()
        repository=FarmRepository(farmDao)
    }
    fun insertItem (farm: com.example.happytree.Farm) {
        viewModelScope.launch (Dispatchers.IO){
            1        }
    }
    fun readItem (): LiveData<List<Farm>>? {
        return repository.readItem()
    }
    fun updateItem (farm: Farm) {
        viewModelScope.launch (Dispatchers.IO){
            repository.updateItem(farm)
        }
    }
    fun deleteItem (farm: Farm) {
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteItem(farm)
        }
    }

}
