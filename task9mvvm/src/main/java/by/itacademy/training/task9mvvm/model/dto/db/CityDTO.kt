package by.itacademy.training.task9mvvm.model.dto.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.itacademy.training.task9mvvm.model.dto.db.CityDTO.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CityDTO(
    @PrimaryKey(autoGenerate = true)
    private val id: Int,
    private val name: String
) {
    companion object {
        const val TABLE_NAME = "city"
    }
}
