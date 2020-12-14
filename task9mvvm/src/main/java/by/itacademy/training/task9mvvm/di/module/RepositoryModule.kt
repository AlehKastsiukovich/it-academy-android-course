package by.itacademy.training.task9mvvm.di.module

import by.itacademy.training.task9mvvm.model.repository.CitiesRepository
import by.itacademy.training.task9mvvm.model.repository.CitiesRepositoryImpl
import by.itacademy.training.task9mvvm.model.repository.WeatherForecastRepository
import by.itacademy.training.task9mvvm.model.repository.WeatherForecastRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindWeatherForeCastRepository(forecast: WeatherForecastRepositoryImpl): WeatherForecastRepository

    @Singleton
    @Binds
    fun bindCitiesRepository(repositoryImpl: CitiesRepositoryImpl): CitiesRepository
}
