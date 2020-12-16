package by.itacademy.training.task9mvp.model.domain

data class HourTemperature(
    val celsiusTemperature: Int,
    val fahrenheitTemperature: Int,
    val time: String,
    val condition: Condition
)
