package by.itacademy.training.task9mvvm.model.repository

import android.util.Log
import by.itacademy.training.task9mvvm.model.api.WeatherApi
import by.itacademy.training.task9mvvm.util.DTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dtoMapper: DTOMapper
) : WeatherForecastRepository {

    override suspend fun getWeatherForecastForDay(city: String) = withContext(Dispatchers.IO) {
        val requestResult = api.getCurrentWeather(city)
        Log.d("TAG", requestResult.toString())
        dtoMapper.invoke(requestResult)
    }
}
