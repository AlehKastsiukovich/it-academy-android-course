package by.itacademy.training.task9mvp.di.module

import android.app.Application
import androidx.room.Room
import by.itacademy.training.task9mvp.db.CitiesDao
import by.itacademy.training.task9mvp.db.CitiesDatabase
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
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideCitiesDao(cityDatabase: CitiesDatabase): CitiesDao = cityDatabase.citiesDao()

    companion object {
        private const val DATABASE_NAME = "cities_database"
    }
}
