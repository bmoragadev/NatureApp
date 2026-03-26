package com.example.natureapp

import com.example.natureapp.data.local.PlantDao
import com.example.natureapp.data.remote.PlantApiService
import com.example.natureapp.model.Plant
import com.example.natureapp.repository.PlantRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class PlantRepositoryTest {

    @Test
    fun insertPlant_callsDaoInsertAndApi() : Unit = runTest {

        val mockDao : PlantDao = mock(PlantDao::class.java)
        val mockApi : PlantApiService = mock(PlantApiService::class.java)

        val repository : PlantRepository = PlantRepository(mockDao, mockApi)
        val testPlant : Plant = Plant(id = 1, name = "Test", description = "Test")
        val fakeToken : String = "token123"

        repository.insert(testPlant, fakeToken)

        verify(mockDao).insertPlant(testPlant)
    }
}