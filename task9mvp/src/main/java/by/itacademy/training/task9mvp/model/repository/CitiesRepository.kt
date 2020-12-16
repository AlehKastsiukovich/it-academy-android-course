package by.itacademy.training.task9mvp.model.repository

import by.itacademy.training.task9mvp.model.domain.City
import io.reactivex.Completable
import io.reactivex.Observable

interface CitiesRepository {

    fun getAllCities(): Observable<List<City>>

    fun insertCity(city: City): Completable
}
