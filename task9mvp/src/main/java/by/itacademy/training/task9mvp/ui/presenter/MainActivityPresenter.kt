package by.itacademy.training.task9mvp.ui.presenter

import by.itacademy.training.task9mvp.model.domain.CurrentTemperature
import by.itacademy.training.task9mvp.model.domain.WeatherReport

interface MainActivityPresenter {

    fun onSwitchTemperatureType(state: Boolean)

    fun onDataLoading()

    fun onErrorData()

    fun onSuccess(weatherReport: WeatherReport)

    fun onOpenCityListButtonPress()

    fun provideDataFromApi()

    fun getCurrentSwitcherState(): Boolean

    fun getCurrentTemperature(currentTemperature: CurrentTemperature): String

    fun onActivityDestroy()
}
