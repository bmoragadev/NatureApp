package com.example.natureapp.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PlantApiService {

    @GET("api/v1/plants")
    suspend fun getPlants(
        @Header("Authorization") token : String,
        @Query("limit") limit: Int = 20
    ) : Response<List<PlantDto>>


    @POST("api/v1/plants")
    suspend fun createPlant(
        @Header("Authorization") token : String,
        @Body plant : PlantDto
    ) : Response<PlantDto>
}