package by.itacademy.training.task9mvvm.util

import android.content.SharedPreferences

interface SupportSharedPreference {

    fun getSharedPreference(): SharedPreferences

    fun setCurrentTemperatureUnit(unit: TemperatureUnit)

    fun getCurrentTemperatureUnit(): TemperatureUnit
}
