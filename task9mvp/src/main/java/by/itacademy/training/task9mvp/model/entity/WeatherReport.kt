package by.itacademy.training.task9mvp.model.entity

data class WeatherReport(
    val currentTemperature: CurrentTemperature,
    val location: Location,
    val forecastDay: ForecastDay,
)
