package by.itacademy.training.task9mvp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.itacademy.training.task9mvp.model.dto.db.City

@Dao
interface CitiesDao {

    @Query("SELECT * FROM city")
    fun getAll(): LiveData<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: City)
}
