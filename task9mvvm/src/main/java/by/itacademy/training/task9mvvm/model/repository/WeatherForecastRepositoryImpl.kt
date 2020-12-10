package by.itacademy.training.task9mvvm.model.repository

import by.itacademy.training.task9mvvm.model.api.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherForecastRepository {

    override suspend fun getWeatherForecastForDay(city: String) = withContext(Dispatchers.IO) {
        api.getCurrentWeather(city)
    }
}
