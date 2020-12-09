package by.itacademy.training.task9mvvm.util

import by.itacademy.training.task9mvvm.model.dto.ApiResponse
import by.itacademy.training.task9mvvm.model.entity.CurrentTemperature
import by.itacademy.training.task9mvvm.model.entity.ForecastDay
import by.itacademy.training.task9mvvm.model.entity.HourTemperature
import by.itacademy.training.task9mvvm.model.entity.Location
import by.itacademy.training.task9mvvm.model.entity.WeatherReport

class DTOMapper : (ApiResponse) -> WeatherReport {

    override fun invoke(resposne: ApiResponse): WeatherReport {
        val hourTemperature = mutableListOf<HourTemperature>()
        resposne.forecast.forecastday[0].hour.forEach {
            hourTemperature.add(HourTemperature(it.temp_c, it.temp_f, it.time))
        }

        return WeatherReport(
            CurrentTemperature(
                resposne.current.temp_c,
                resposne.current.temp_f
            ),
            Location(
                resposne.location.country,
                resposne.location.localtime,
                resposne.location.name,
                resposne.location.region
            ),
            ForecastDay(
                hourTemperature
            )
        )
    }
}
