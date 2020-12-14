package by.itacademy.training.task9mvvm.model.repository

import androidx.lifecycle.LiveData
import by.itacademy.training.task9mvvm.db.CitiesDao
import by.itacademy.training.task9mvvm.model.dto.db.City
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesDao: CitiesDao
) : CitiesRepository {

    override fun getAllCities(): LiveData<List<City>> = citiesDao.getAll()

    override suspend fun insertCity(city: City) = citiesDao.insert(city)
}
