package by.itacademy.training.task9mvvm.model.repository

import by.itacademy.training.task9mvvm.model.entity.WeatherReport

interface WeatherForecastRepository {

    suspend fun getWeatherForecastForDay(city: String): WeatherReport
}
