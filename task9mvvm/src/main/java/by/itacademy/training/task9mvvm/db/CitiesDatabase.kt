package by.itacademy.training.task9mvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.itacademy.training.task9mvvm.model.dto.db.City

@Database(entities = [City::class], version = 2)
abstract class CitiesDatabase : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao

    companion object {
        fun getCityDatabase(context: Context): CitiesDatabase =
            Room.databaseBuilder(
                context,
                CitiesDatabase::class.java,
                "cities_database"
            )
                .build()
    }
}
