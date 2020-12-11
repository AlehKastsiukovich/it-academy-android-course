package by.itacademy.training.task9mvvm.util

import javax.inject.Inject
import kotlin.math.roundToInt

class TemperatureFormatter @Inject constructor() {

    fun convertTimeData(time: String) = time.substring(time.length - LAST_TIME_DIGITS)

    fun roundTemperature(temperature: Double) = temperature.roundToInt()

    companion object {
        private const val LAST_TIME_DIGITS = 5
    }
}
