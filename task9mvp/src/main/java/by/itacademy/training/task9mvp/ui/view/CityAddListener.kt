package by.itacademy.training.task9mvp.ui.view

import by.itacademy.training.task9mvp.model.dto.db.CityDto

interface CityAddListener {

    fun onCityAdd(city: CityDto)
}
