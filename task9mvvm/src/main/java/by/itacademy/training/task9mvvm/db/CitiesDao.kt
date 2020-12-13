package by.itacademy.training.task9mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.itacademy.training.task9mvvm.model.dto.db.CityDTO
import by.itacademy.training.task9mvvm.model.dto.db.CityDTO.Companion.TABLE_NAME

@Dao
interface CitiesDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): LiveData<CityDTO>

    @Insert
    fun insert(cityDTO: CityDTO)
}
