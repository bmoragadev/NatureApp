package com.example.natureapp.data.remote

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class PlantDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("description")
    val description : String
)
