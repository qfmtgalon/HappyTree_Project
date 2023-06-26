package com.example.happytree.database.FarmDatabase

import androidx.lifecycle.LiveData

class FarmRepository (private val farmDao: FarmDao){
    suspend fun insertItem(farm: Farm){
        farmDao.insert(farm)
    }
    fun readItem(): LiveData<List<Farm>>?{
        return farmDao.getDiseaseData()
    }
    suspend fun updateItem(farm: Farm){
        farmDao.updateFarm(farm)
    }
    suspend fun deleteItem(farm: Farm){
        farmDao.deleteFarm(farm)
    }
}