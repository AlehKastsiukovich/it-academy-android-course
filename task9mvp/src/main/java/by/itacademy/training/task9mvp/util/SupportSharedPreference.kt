package by.itacademy.training.task9mvp.util

import android.content.SharedPreferences
import by.itacademy.training.task9mvp.model.domain.TemperatureUnit

interface SupportSharedPreference {

    fun getSharedPreference(): SharedPreferences

    fun setCurrentTemperatureUnit(unit: TemperatureUnit)

    fun getCurrentTemperatureUnit(): TemperatureUnit

    fun getCurrentCity(): String?

    fun setCurrentCity(city: String)
}
