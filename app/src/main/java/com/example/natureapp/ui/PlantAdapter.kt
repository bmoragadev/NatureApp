package com.example.natureapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.natureapp.databinding.ItemPlantBinding
import com.example.natureapp.model.Plant

class PlantAdapter(
    private var plants : List<Plant>,
    private val onPlantClick: (Plant) -> Unit,
) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    class PlantViewHolder(val binding : ItemPlantBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlantViewHolder {
        val binding = ItemPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plants[position]

        holder.binding.tvPlantName.text = plant.name
        holder.binding.tvPlantDescription.text = plant.description
        holder.binding.ivPlantIcon.setImageResource(plant.imageResId)

        holder.binding.root.setOnClickListener {
            onPlantClick(plant)
        }

    }

    override fun getItemCount(): Int = plants.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newPlants : List<Plant>) : Unit {
        this.plants = newPlants
        notifyDataSetChanged()
    }

}