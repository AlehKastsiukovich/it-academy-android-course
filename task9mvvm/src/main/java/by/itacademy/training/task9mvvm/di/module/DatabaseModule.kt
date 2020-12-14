package by.itacademy.training.task9mvvm.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import by.itacademy.training.task9mvvm.db.CitiesDao
import by.itacademy.training.task9mvvm.db.CitiesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCitiesDatabase(application: Application): CitiesDatabase {
        return Room.databaseBuilder(
            application,
            CitiesDatabase::class.java,
            "cities_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCitiesDao(cityDatabase: CitiesDatabase): CitiesDao = cityDatabase.citiesDao()
}
