package by.itacademy.training.task9mvp.model.entity

import by.itacademy.training.task9mvp.model.entity.Condition

data class HourTemperature(
    val celsiusTemperature: Int,
    val fahrenheitTemperature: Int,
    val time: String,
    val condition: Condition
)
