package com.example.happytree.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.happytree.HappyTreeApplication

@Database(entities = [Auth::class], version = 1, exportSchema = false)
abstract class AuthDatabase : RoomDatabase() {
    abstract val authDao: AuthDao

    companion object {
        @Volatile
        private var INSTANCE: AuthDatabase? = null

        fun getInstance(context: Context): AuthDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AuthDatabase::class.java,
                        "auth_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}


//@Database(entities = [Auth::class], version = 3, exportSchema = false)
//abstract class AuthDatabase: RoomDatabase(){
//    abstract fun AuthDao(): AuthDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: AuthDatabase? = null
//
//            fun getDatabase(context: Context): AuthDatabase{
//                val tempInstance = INSTANCE
//                if(tempInstance!=null) {
//                    return tempInstance
//                }
//                synchronized(this){
//                    val instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        AuthDatabase::class.java,
//                        "database"
//                    ).fallbackToDestructiveMigration()
//                        .allowMainThreadQueries()
//                        .build()
//                    INSTANCE = instance
//                    return instance
//            }
//        }
//
//        fun getInstance(context: Context): AuthDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AuthDatabase::class.java,
//                    "database"
//                )
//                    .fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//
//    }
//}