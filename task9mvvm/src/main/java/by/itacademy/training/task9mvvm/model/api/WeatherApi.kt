package by.itacademy.training.task9mvvm.model.api

import by.itacademy.training.task9mvvm.model.dto.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json?key=$WEATHER_API_KEY")
    suspend fun getCurrentWeather(@Query("q") city: String): Result

    companion object {
        const val BASE_URL = "http://api.weatherapi.com/v1/"
        const val WEATHER_API_KEY = "2f341218fbc94a1496094754200912"
    }
}
