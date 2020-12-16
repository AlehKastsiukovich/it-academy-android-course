package by.itacademy.training.task9mvp.model.repository

import by.itacademy.training.task9mvp.model.api.WeatherApi
import by.itacademy.training.task9mvp.model.entity.WeatherReport
import by.itacademy.training.task9mvp.util.DTOMapper
import io.reactivex.Observable
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dtoMapper: DTOMapper
) : WeatherForecastRepository {

    override fun getWeatherForecastForDay(city: String): Observable<WeatherReport> =
        api.getCurrentWeather(city).map {
            dtoMapper.invoke(it)
        }
}
