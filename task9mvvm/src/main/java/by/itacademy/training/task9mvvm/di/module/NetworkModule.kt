package by.itacademy.training.task9mvvm.di.module

import by.itacademy.training.task9mvvm.model.api.WeatherApi
import by.itacademy.training.task9mvvm.model.api.WeatherApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    companion object {
        const val BASE_URL = "http://api.weatherapi.com/v1/"
    }
}
