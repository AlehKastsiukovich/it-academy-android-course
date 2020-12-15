package by.itacademy.training.task9mvp.model.dto.api

data class ApiResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)

