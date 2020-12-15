package by.itacademy.training.task9mvp.util

import by.itacademy.training.task9mvp.model.entity.CurrentTemperature
import by.itacademy.training.task9mvp.model.entity.HourTemperature

interface CurrentTemperatureUnitListener {

    fun onCelsiusTurnOn()

    fun onFahrenheitTurnOn()

    fun getCurrentTemperatureUnitState(): Boolean

    fun getCurrentTemperature(currentTemperature: CurrentTemperature?): String

    fun getHourTemperature(hourTemperature: HourTemperature): String
}
