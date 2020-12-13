package by.itacademy.training.task9mvvm.model.dto.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
class CityDTO(
    @PrimaryKey(autoGenerate = true)
    private val id: Int,
    private val name: String
)
