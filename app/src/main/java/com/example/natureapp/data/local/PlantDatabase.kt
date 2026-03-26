package com.example.natureapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.natureapp.model.Plant


@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class PlantDatabase : RoomDatabase() {

    abstract fun plantDao() : PlantDao

    companion object {

        @Volatile
        private var INSTANCE : PlantDatabase? = null

        fun getDatabase(context : Context) : PlantDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantDatabase::class.java,
                    "nature_app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }


    }
}