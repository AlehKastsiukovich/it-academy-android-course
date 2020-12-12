package by.itacademy.training.task9mvvm.util

import android.content.Context
import android.content.SharedPreferences

class SupportSharedPreferenceImpl(private val context: Context) : SupportSharedPreference {

    override fun getSharedPreference(): SharedPreferences =
        context.getSharedPreferences(TEMPERATURE_UNITS, Context.MODE_PRIVATE)

    override fun setCurrentTemperatureUnit() {
        getSharedPreference().edit().putString()
    }

    override fun getCurrentTemperatureUnit() {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TEMPERATURE_UNITS = "temperatureUnits"
    }
}
