package by.itacademy.training.task9mvvm.model.entity

import by.itacademy.training.task9mvvm.model.dto.Condition

data class CurrentTemperature(
    val celsiusTemperature: Int,
    val fahrenheitTemperature: Int,
    val condition: Condition
)
