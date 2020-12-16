package by.itacademy.training.task9mvvm.ui.adapter

import by.itacademy.training.task9mvvm.model.domain.CurrentTemperature
import by.itacademy.training.task9mvvm.model.domain.HourTemperature

interface CurrentTemperatureUnitListener {

    fun onCelsiusTurnOn()

    fun onFahrenheitTurnOn()

    fun getCurrentTemperatureUnitState(): Boolean

    fun getCurrentTemperature(currentTemperature: CurrentTemperature?): String

    fun getHourTemperature(hourTemperature: HourTemperature): String
}
