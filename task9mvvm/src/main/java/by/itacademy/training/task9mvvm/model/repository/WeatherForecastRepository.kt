package by.itacademy.training.task9mvvm.model.repository

import by.itacademy.training.task9mvvm.model.dto.ApiResponse

interface WeatherForecastRepository {

    suspend fun getWeatherForecastForDay(city: String): ApiResponse
}
