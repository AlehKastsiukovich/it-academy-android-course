package by.itacademy.training.task9mvvm.util

import android.content.SharedPreferences
import by.itacademy.training.task9mvvm.model.domain.TemperatureUnit

interface SupportSharedPreference {

    fun getSharedPreference(): SharedPreferences

    fun setCurrentTemperatureUnit(unit: TemperatureUnit)

    fun getCurrentTemperatureUnit(): TemperatureUnit

    fun getCurrentCity(): String?

    fun setCurrentCity(city: String)
}
