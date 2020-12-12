package by.itacademy.training.task9mvvm.util

import by.itacademy.training.task9mvvm.model.entity.CurrentTemperature
import by.itacademy.training.task9mvvm.model.entity.HourTemperature

interface CurrentTemperatureUnitListener {

    fun onCelsiusTurnOn()

    fun onFahrenheitTurnOn()

    fun getCurrentTemperatureUnitState(): Boolean

    fun getCurrentTemperature(currentTemperature: CurrentTemperature?): String

    fun getHourTemperature(hourTemperature: HourTemperature): String
}
