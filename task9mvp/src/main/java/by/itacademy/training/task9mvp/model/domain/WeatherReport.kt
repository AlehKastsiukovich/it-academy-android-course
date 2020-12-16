package by.itacademy.training.task9mvp.model.domain

data class WeatherReport(
    val currentTemperature: CurrentTemperature,
    val location: Location,
    val forecastDay: ForecastDay,
)
