package com.example.happytree.database.FarmDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FarmViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FarmRepository

    init {
        // Initializing the repository using the FarmDao from the FarmDatabase
        val farmDao = FarmDatabase.getDatabase(application).FarmDao()
        repository = FarmRepository(farmDao)
    }

    // Function to insert a farm item using a coroutine on the IO dispatcher
    fun insertItem(farm: Farm) {
        viewModelScope.launch(Dispatchers.IO) {
            // Insert the farm item using the repository
            repository.insertItem(farm)
        }
    }

    // Function to read farm items as LiveData from the repository
    fun readItem(): LiveData<List<Farm>>? {
        return repository.readItem()
    }

    // Function to update a farm item using a coroutine on the IO dispatcher
    fun updateItem(farm: Farm) {
        viewModelScope.launch(Dispatchers.IO) {
            // Update the farm item using the repository
            repository.updateItem(farm)
        }
    }

    // Function to delete a farm item using a coroutine on the IO dispatcher
    fun deleteItem(farm: Farm) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete the farm item using the repository
            repository.deleteItem(farm)
        }
    }

    // Function to delete all farm items using a coroutine on the IO dispatcher
    fun deleteAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete all farm items using the repository
            repository.deleteAllItems()
        }
    }
}
