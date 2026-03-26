package com.example.natureapp.repository

import com.example.natureapp.R
import com.example.natureapp.model.Plant
import kotlinx.coroutines.delay

class PlantRepository {

    suspend fun getPlants() : List<Plant> {
        delay(1500)
        return listOf(
            Plant(1, "Aloe Vera", "Riego escaso, propagar por hijuelos."),
            Plant(2, "Monstera Deliciosa", "Luz indirecta brillante. Humedad alta."),
            Plant(3, "Venus Atrapamoscas", "Planta carnívora; requiere agua destilada.")
        )
    }
}