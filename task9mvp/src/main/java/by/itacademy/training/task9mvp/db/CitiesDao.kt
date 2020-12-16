package by.itacademy.training.task9mvp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.itacademy.training.task9mvp.model.dto.db.CityDto
import io.reactivex.Observable

@Dao
interface CitiesDao {

    @Query("SELECT * FROM city")
    fun getAll(): Observable<List<CityDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: CityDto)
}
