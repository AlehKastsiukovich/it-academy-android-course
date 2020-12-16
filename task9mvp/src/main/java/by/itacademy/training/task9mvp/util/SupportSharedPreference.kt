package by.itacademy.training.task9mvp.util

import android.content.SharedPreferences

interface SupportSharedPreference {

    fun getSharedPreference(): SharedPreferences

    fun setCurrentTemperatureUnit(unit: TemperatureUnit)

    fun getCurrentTemperatureUnit(): TemperatureUnit

    fun getCurrentCity(): String?

    fun setCurrentCity(city: String)
}
