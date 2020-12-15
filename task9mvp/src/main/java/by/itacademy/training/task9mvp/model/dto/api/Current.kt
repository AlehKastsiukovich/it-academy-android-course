package by.itacademy.training.task9mvp.model.dto.api

import by.itacademy.training.task9mvp.model.dto.api.Condition

data class Current(
    val temp_c: Double,
    val temp_f: Double,
    val condition: Condition
)
