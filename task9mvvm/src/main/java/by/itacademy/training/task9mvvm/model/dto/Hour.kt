package by.itacademy.training.task9mvvm.model.dto

data class Hour(
    val temp_c: Double,
    val temp_f: Double,
    val time: String,
    val condition: Condition
)
