package by.itacademy.training.task9mvp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.itacademy.training.task9mvp.model.dto.db.CityDto

@Database(entities = [CityDto::class], version = 2)
abstract class CitiesDatabase : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao
}
