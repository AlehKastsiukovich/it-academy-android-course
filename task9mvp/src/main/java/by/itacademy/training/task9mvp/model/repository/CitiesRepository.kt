package by.itacademy.training.task9mvp.model.repository

import by.itacademy.training.task9mvp.model.dto.db.CityDto
import io.reactivex.Observable

interface CitiesRepository {

    fun getAllCities(): Observable<List<CityDto>>

    fun insertCity(city: CityDto)
}
