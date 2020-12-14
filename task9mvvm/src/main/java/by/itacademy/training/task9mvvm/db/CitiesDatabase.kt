package by.itacademy.training.task9mvvm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.itacademy.training.task9mvvm.model.dto.db.City

@Database(entities = [City::class], version = 2)
abstract class CitiesDatabase : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao
}
