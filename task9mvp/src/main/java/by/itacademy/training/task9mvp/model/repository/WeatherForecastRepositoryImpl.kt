package by.itacademy.training.task9mvp.model.repository

import by.itacademy.training.task9mvp.model.api.WeatherApi
import by.itacademy.training.task9mvp.model.domain.WeatherReport
import io.reactivex.Single
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dtoMapper: WeatherReportMapper
) : WeatherForecastRepository {

    override fun getWeatherForecastForDay(city: String): Single<WeatherReport> =
        api.getCurrentWeather(city).map {
            dtoMapper.invoke(it)
        }
}
