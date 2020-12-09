package by.itacademy.training.task9mvvm.model.entity

data class WeatherReport(
    val currentTemperature: CurrentTemperature,
    val location: Location,
    val forecastDay: ForecastDay,
)
