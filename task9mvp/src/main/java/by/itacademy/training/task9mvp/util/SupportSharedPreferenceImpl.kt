package by.itacademy.training.task9mvp.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SupportSharedPreferenceImpl @Inject constructor(
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

    override fun getCurrentCity() = getSharedPreference().getString(KEY_CITY, DEFAULT_CITY)

    override fun setCurrentCity(city: String) {
        getSharedPreference().edit().putString(KEY_CITY, city).apply()
    }

    companion object {
        const val TEMPERATURE_UNITS = "temperatureUnits"
        const val TEMPERATURE_UNIT = "unit"
        const val DEFAULT_CITY = "Minsk"
        const val KEY_CITY = "KeyCity"
    }
}
