package com.example.natureapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.natureapp.R

@Entity(tableName = "plants_table")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "plant_name")
    val name : String,
    @ColumnInfo(name = "plant_description")
    val description : String,
    @ColumnInfo(name = "image_res_id")
    val imageResId : Int = R.drawable.ic_tree
)