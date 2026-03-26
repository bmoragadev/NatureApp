package com.example.natureapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.natureapp.model.Plant

@Dao
interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plant : Plant) : Unit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlants(plants : List<Plant>) : Unit

    @Query("SELECT * FROM plants_table ORDER BY plant_name ASC")
    fun getAllPlants() : LiveData<List<Plant>>

}