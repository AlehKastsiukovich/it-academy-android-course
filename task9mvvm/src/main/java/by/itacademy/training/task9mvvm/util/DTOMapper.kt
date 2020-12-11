package by.itacademy.training.task9mvvm.util

import by.itacademy.training.task9mvvm.model.dto.ApiResponse
import by.itacademy.training.task9mvvm.model.entity.CurrentTemperature
import by.itacademy.training.task9mvvm.model.entity.ForecastDay
import by.itacademy.training.task9mvvm.model.entity.HourTemperature
import by.itacademy.training.task9mvvm.model.entity.Location
import by.itacademy.training.task9mvvm.model.entity.WeatherReport
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DTOMapper @Inject constructor(private val formatter: TemperatureFormatter) :
    (ApiResponse) -> WeatherReport {

    override fun invoke(resposne: ApiResponse): WeatherReport {
        val hourTemperature = mutableListOf<HourTemperature>()
        resposne.forecast.forecastday[0].hour.forEach {
            hourTemperature.add(
                HourTemperature(
                    formatter.roundTemperature(it.temp_c),
                    formatter.roundTemperature(it.temp_f),
                    formatter.convertTimeData(it.time)
                )
            )
        }

        return WeatherReport(
            CurrentTemperature(
                formatter.roundTemperature(resposne.current.temp_c),
                formatter.roundTemperature(resposne.current.temp_f)
            ),
            Location(
                resposne.location.country,
                resposne.location.localtime,
                resposne.location.name,
                resposne.location.region
            ),
            ForecastDay(hourTemperature)
        )
    }
}
