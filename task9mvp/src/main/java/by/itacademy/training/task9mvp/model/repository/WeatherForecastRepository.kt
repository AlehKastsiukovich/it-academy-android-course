package by.itacademy.training.task9mvp.model.repository

import by.itacademy.training.task9mvp.model.entity.WeatherReport

interface WeatherForecastRepository {

    suspend fun getWeatherForecastForDay(city: String): WeatherReport
}
