package com.example.happytree.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FarmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(farm: Farm)

    @Query("SELECT * FROM farmTable")
    fun getDiseaseData(): LiveData<List<Farm>>?

    @Update
    suspend fun updateFarm(farm: Farm)

    @Delete
    suspend fun deleteFarm(farm: Farm)
}