package by.itacademy.training.task9mvp.model.repository

import by.itacademy.training.task9mvp.model.domain.WeatherReport
import io.reactivex.Single

interface WeatherForecastRepository {

    fun getWeatherForecastForDay(city: String): Single<WeatherReport>
}
