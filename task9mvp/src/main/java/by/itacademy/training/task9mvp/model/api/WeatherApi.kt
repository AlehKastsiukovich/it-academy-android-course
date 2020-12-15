package by.itacademy.training.task9mvp.model.api

import by.itacademy.training.task9mvp.model.dto.api.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json?key=$WEATHER_API_KEY")
    suspend fun getCurrentWeather(@Query("q") city: String): ApiResponse

    companion object {
        const val BASE_URL = "http://api.weatherapi.com/v1/"
        const val WEATHER_API_KEY = "b76ac50d25e9479ba3a123430201212"
    }
}
