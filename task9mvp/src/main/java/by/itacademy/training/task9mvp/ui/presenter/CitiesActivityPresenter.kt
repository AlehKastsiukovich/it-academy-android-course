package by.itacademy.training.task9mvp.ui.presenter

import by.itacademy.training.task9mvp.model.dto.db.CityDto

interface CitiesActivityPresenter {

    fun onDataLoading()

    fun onErrorData()

    fun onSuccess(cityList: List<CityDto>)

    fun provideDataFromDatabase()
}
