package by.itacademy.training.task9mvp.ui.presenter

import by.itacademy.training.task9mvp.model.entity.City

interface CitiesActivityPresenter {

    fun onDataLoading()

    fun onErrorData()

    fun onSuccess(cityList: List<City>)

    fun provideCitiesFromDatabase()

    fun addCity(city: City)
}
