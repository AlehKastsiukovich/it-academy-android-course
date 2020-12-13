package by.itacademy.training.task9mvvm.model.repository

import androidx.lifecycle.LiveData
import by.itacademy.training.task9mvvm.model.dto.db.City

interface CitiesRepository {

    fun getAllCities(): LiveData<List<City>>

    suspend fun insertCity(city: City)
}
