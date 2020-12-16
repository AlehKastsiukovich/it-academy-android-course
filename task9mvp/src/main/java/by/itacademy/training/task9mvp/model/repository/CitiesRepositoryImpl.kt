package by.itacademy.training.task9mvp.model.repository

import by.itacademy.training.task9mvp.db.CitiesDao
import by.itacademy.training.task9mvp.model.dto.db.CityDto
import io.reactivex.Observable
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesDao: CitiesDao
) : CitiesRepository {

    override fun getAllCities(): Observable<List<CityDto>> = citiesDao.getAll()

    override fun insertCity(city: CityDto) = citiesDao.insert(city)
}
