package com.example.happytree.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Farm::class], version = 3, exportSchema = false)
abstract class FarmDatabase: RoomDatabase(){
    abstract fun FarmDao(): FarmDao

    companion object {
        @Volatile
        private var INSTANCE: FarmDatabase? = null
        fun getDatabase(context: Context): FarmDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FarmDatabase::class.java,
                    "database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}