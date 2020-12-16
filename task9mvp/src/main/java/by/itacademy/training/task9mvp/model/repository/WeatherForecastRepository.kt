package by.itacademy.training.task9mvp.model.repository

import by.itacademy.training.task9mvp.model.entity.WeatherReport
import io.reactivex.Observable

interface WeatherForecastRepository {

    fun getWeatherForecastForDay(city: String): Observable<WeatherReport>
}
