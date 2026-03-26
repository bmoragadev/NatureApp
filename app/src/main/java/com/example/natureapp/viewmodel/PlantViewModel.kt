package com.example.natureapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.natureapp.data.local.PlantDatabase
import com.example.natureapp.data.remote.RetrofitClient
import com.example.natureapp.model.Plant
import com.example.natureapp.repository.PlantRepository
import kotlinx.coroutines.launch
class PlantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : PlantRepository
    var plants : LiveData<List<Plant>>

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading : LiveData<Boolean> get() = _isLoading

    private val fakeAuthToken : String = "Bearer mi_super_token_secreto_123"


    init {
        val plantDao = PlantDatabase.getDatabase(application).plantDao()
        val apiService = RetrofitClient.apiService

        repository = PlantRepository(plantDao, apiService)
        plants = repository.allPlants
    }

    fun syncDataRemote() : Unit {
        viewModelScope.launch {
            _isLoading.value = true
            repository.refreshPlantsRemote(fakeAuthToken)
            _isLoading.value = false
        }
    }

    fun addPlant(newPlant : Plant) : Unit {
        viewModelScope.launch {
            repository.insert(newPlant, fakeAuthToken)
        }
    }



}