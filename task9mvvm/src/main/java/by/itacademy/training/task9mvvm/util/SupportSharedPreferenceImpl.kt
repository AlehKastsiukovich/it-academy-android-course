package by.itacademy.training.task9mvvm.util

import android.content.Context
import android.content.SharedPreferences

class SupportSharedPreferenceImpl(
    private val context: Context
) : SupportSharedPreference {

    override fun getSharedPreference(): SharedPreferences =
        context.getSharedPreferences(TEMPERATURE_UNITS, Context.MODE_PRIVATE)

    override fun setCurrentTemperatureUnit(unit: TemperatureUnit) =
        getSharedPreference().edit().putString(TEMPERATURE_UNIT, unit.name).apply()

    override fun getCurrentTemperatureUnit(): TemperatureUnit {
        val currentTemperatureUnit = getSharedPreference()
            .getString(TEMPERATURE_UNIT, TemperatureUnit.CELSIUS.name)
        return TemperatureUnit.valueOf(currentTemperatureUnit ?: TemperatureUnit.CELSIUS.name)
    }

    companion object {
        const val TEMPERATURE_UNITS = "temperatureUnits"
        const val TEMPERATURE_UNIT = "unit"
    }
}
