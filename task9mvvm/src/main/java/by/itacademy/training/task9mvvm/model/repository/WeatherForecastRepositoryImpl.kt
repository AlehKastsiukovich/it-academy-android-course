package by.itacademy.training.task9mvvm.model.repository

import by.itacademy.training.task9mvvm.model.api.WeatherApi
import by.itacademy.training.task9mvvm.model.dto.Result
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherForecastRepository {

    override suspend fun getWeatherForecastForDay(city: String): Result {
        return api.getCurrentWeather(city)
    }
}
