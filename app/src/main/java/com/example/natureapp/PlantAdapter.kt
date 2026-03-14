package com.example.natureapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.natureapp.databinding.ItemPlantBinding
import models.Plant

class PlantAdapter(
    private val plants : List<Plant>,
    private val onPlantClick: (Plant) -> Unit,
) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    class PlantViewHolder(val binding : ItemPlantBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlantAdapter.PlantViewHolder {
        val binding = ItemPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantAdapter.PlantViewHolder, position: Int) {
        val plant = plants[position]

        holder.binding.tvPlantName.text = plant.name
        holder.binding.tvPlantDescription.text = plant.description
        holder.binding.ivPlantIcon.setImageResource(plant.imageResId)

        holder.binding.root.setOnClickListener {
            onPlantClick(plant)
        }

    }

    override fun getItemCount(): Int = plants.size

}