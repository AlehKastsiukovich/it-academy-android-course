package by.itacademy.training.task9mvp.model.repository

import by.itacademy.training.task9mvp.model.api.WeatherApi
import by.itacademy.training.task9mvp.util.DTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dtoMapper: DTOMapper
) : WeatherForecastRepository {

    override suspend fun getWeatherForecastForDay(city: String) = withContext(Dispatchers.IO) {
        val requestResult = api.getCurrentWeather(city)
        dtoMapper.invoke(requestResult)
    }
}
