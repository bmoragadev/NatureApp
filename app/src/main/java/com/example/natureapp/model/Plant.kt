package com.example.natureapp.model

import com.example.natureapp.R

data class Plant(
    val id : Int,
    val name : String,
    val description : String,
    val imageResId : Int = R.drawable.ic_tree
)