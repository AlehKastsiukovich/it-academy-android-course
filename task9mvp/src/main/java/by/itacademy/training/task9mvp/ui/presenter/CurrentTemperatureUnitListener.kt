package by.itacademy.training.task9mvp.ui.presenter

import by.itacademy.training.task9mvp.model.domain.CurrentTemperature
import by.itacademy.training.task9mvp.model.domain.HourTemperature

interface CurrentTemperatureUnitListener {

    fun onCelsiusTurnOn()

    fun onFahrenheitTurnOn()

    fun getCurrentTemperatureUnitState(): Boolean

    fun getCurrentTemperature(currentTemperature: CurrentTemperature?): String

    fun getHourTemperature(hourTemperature: HourTemperature): String
}
