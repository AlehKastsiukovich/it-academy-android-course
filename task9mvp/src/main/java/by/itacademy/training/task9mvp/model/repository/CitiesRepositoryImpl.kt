package by.itacademy.training.task9mvp.model.repository

import by.itacademy.training.task9mvp.db.CitiesDao
import by.itacademy.training.task9mvp.model.dto.db.CityDto
import by.itacademy.training.task9mvp.model.entity.City
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesDao: CitiesDao
) : CitiesRepository {

    override fun getAllCities(): Observable<List<City>> =
        citiesDao.getAll()
            .subscribeOn(Schedulers.io())
            .map {
                val cityList = mutableListOf<City>()
                it.forEach { cityDto -> cityList.add(City(cityDto.name)) }
                cityList
            }

    override fun insertCity(city: City): Completable = citiesDao.insert(CityDto(city.cityName))
}
