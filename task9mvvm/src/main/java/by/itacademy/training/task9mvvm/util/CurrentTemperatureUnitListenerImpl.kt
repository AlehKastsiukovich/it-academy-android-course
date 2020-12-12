package by.itacademy.training.task9mvvm.util

import by.itacademy.training.task9mvvm.model.entity.CurrentTemperature
import by.itacademy.training.task9mvvm.model.entity.HourTemperature

class CurrentTemperatureUnitListenerImpl constructor(
    private val sharedPreference: SupportSharedPreference
) : CurrentTemperatureUnitListener {

    override fun onCelsiusTurnOn() {
        sharedPreference.setCurrentTemperatureUnit(TemperatureUnit.CELSIUS)
    }

    override fun onFahrenheitTurnOn() {
        sharedPreference.setCurrentTemperatureUnit(TemperatureUnit.FAHRENHEIT)
    }

    override fun getCurrentTemperatureUnitState(): Boolean =
        when (sharedPreference.getCurrentTemperatureUnit()) {
            TemperatureUnit.CELSIUS -> false
            TemperatureUnit.FAHRENHEIT -> true
        }

    override fun getCurrentTemperature(currentTemperature: CurrentTemperature?) =
        when (sharedPreference.getCurrentTemperatureUnit()) {
            TemperatureUnit.CELSIUS -> currentTemperature?.celsiusTemperature.toString()
            TemperatureUnit.FAHRENHEIT -> currentTemperature?.fahrenheitTemperature.toString()
        }

    override fun getHourTemperature(hourTemperature: HourTemperature) =
        when (sharedPreference.getCurrentTemperatureUnit()) {
            TemperatureUnit.CELSIUS -> hourTemperature.celsiusTemperature.toString()
            TemperatureUnit.FAHRENHEIT -> hourTemperature.fahrenheitTemperature.toString()
        }
}
