package com.example.natureapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.natureapp.model.Plant
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlantViewModel : ViewModel() {

    private val _plants: MutableLiveData<List<Plant>> = MutableLiveData(emptyList())
    val plants: LiveData<List<Plant>> get() = _plants

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchPlants(): Unit {
        if (_plants.value?.isNotEmpty() == true) return

        viewModelScope.launch {
            _isLoading.value = true
            delay(1500)

            val initialList: List<Plant> = listOf(
                Plant(1, "Aloe Vera", "Riego escaso, propagar por hijuelos."),
                Plant(2, "Monstera Deliciosa", "Luz indirecta brillante. Humedad alta."),
                Plant(3, "Venus Atrapamoscas", "Planta carnívora; requiere agua destilada.")
            )

            _plants.value = initialList
            _isLoading.value = false
        }
    }

    fun addPlant(newPlant: Plant): Unit {
        val currentList: List<Plant> = _plants.value ?: emptyList()
        val updatedList: MutableList<Plant> = currentList.toMutableList()

        updatedList.add(newPlant)
        _plants.value = updatedList
    }
}