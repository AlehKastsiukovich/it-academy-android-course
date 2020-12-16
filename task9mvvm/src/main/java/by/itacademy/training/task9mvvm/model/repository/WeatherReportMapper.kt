package by.itacademy.training.task9mvvm.model.repository

import by.itacademy.training.task9mvvm.model.domain.Condition
import by.itacademy.training.task9mvvm.model.domain.CurrentTemperature
import by.itacademy.training.task9mvvm.model.domain.ForecastDay
import by.itacademy.training.task9mvvm.model.domain.HourTemperature
import by.itacademy.training.task9mvvm.model.domain.Location
import by.itacademy.training.task9mvvm.model.domain.WeatherReport
import by.itacademy.training.task9mvvm.model.dto.api.ApiResponse
import by.itacademy.training.task9mvvm.util.Formatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherReportMapper @Inject constructor(private val formatter: Formatter) :
    (ApiResponse) -> WeatherReport {

    override fun invoke(resposne: ApiResponse): WeatherReport {
        val hourTemperature = mapHourTemperature(resposne)
        return WeatherReport(
            CurrentTemperature(
                formatter.roundTemperature(resposne.current.temp_c),
                formatter.roundTemperature(resposne.current.temp_f),
                Condition(
                    resposne.current.condition.text,
                    formatter.formatLink(resposne.current.condition.icon)
                )
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

    private fun mapHourTemperature(response: ApiResponse): List<HourTemperature> {
        val hourTemperature = mutableListOf<HourTemperature>()
        response.forecast.forecastday[0].hour.forEach {
            hourTemperature.add(
                HourTemperature(
                    formatter.roundTemperature(it.temp_c),
                    formatter.roundTemperature(it.temp_f),
                    formatter.convertTimeData(it.time),
                    Condition(
                        it.condition.text,
                        formatter.formatLink(it.condition.icon)
                    )
                )
            )
        }
        return hourTemperature
    }
}
