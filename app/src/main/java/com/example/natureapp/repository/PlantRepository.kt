package com.example.natureapp.repository

import androidx.lifecycle.LiveData
import com.example.natureapp.data.local.PlantDao
import com.example.natureapp.data.remote.PlantApiService
import com.example.natureapp.data.remote.PlantDto
import com.example.natureapp.data.remote.RetrofitClient.apiService
import com.example.natureapp.model.Plant
import retrofit2.Response

class PlantRepository(private val plantDao : PlantDao, private val apiService: PlantApiService) {

    val allPlants : LiveData<List<Plant>> = plantDao.getAllPlants()


    suspend fun refreshPlantsRemote(token : String){
        try {
            val response : Response<List<PlantDto>> = apiService.getPlants(token)

            if(response.isSuccessful){
                val dtos : List<PlantDto>? = response.body()

                dtos?.let { list : List<PlantDto> ->
                    val entities : List<Plant> = list.map {
                        dto : PlantDto ->
                        Plant(
                            id = dto.id,
                            name = dto.name,
                            description = dto.name
                        )
                    }
                    plantDao.insertPlants(entities)
                }
            }

        }catch (e : Exception){
            e.printStackTrace()
        }
    }



    suspend fun insert(plant : Plant, token : String) : Unit {
        plantDao.insertPlant(plant)

        try {
            val dto : PlantDto = PlantDto(id = plant.id, name = plant.name, description = plant.description)
            apiService.createPlant(token, dto)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}