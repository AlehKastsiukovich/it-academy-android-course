package by.itacademy.training.task9mvp.model.dto.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class City(@PrimaryKey val name: String) {

    companion object {
        const val TABLE_NAME = "city"
    }
}
