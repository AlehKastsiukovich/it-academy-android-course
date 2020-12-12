package by.itacademy.training.task9mvvm.util

import by.itacademy.training.task9mvvm.model.dto.ApiResponse
import by.itacademy.training.task9mvvm.model.entity.Condition
import by.itacademy.training.task9mvvm.model.entity.CurrentTemperature
import by.itacademy.training.task9mvvm.model.entity.ForecastDay
import by.itacademy.training.task9mvvm.model.entity.HourTemperature
import by.itacademy.training.task9mvvm.model.entity.Location
import by.itacademy.training.task9mvvm.model.entity.WeatherReport
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DTOMapper @Inject constructor(private val formatter: MetricFormatter) :
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
