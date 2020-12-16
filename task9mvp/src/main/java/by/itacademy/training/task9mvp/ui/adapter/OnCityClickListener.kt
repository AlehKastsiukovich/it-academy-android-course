package by.itacademy.training.task9mvp.ui.adapter

import by.itacademy.training.task9mvp.model.dto.db.CityDto

interface OnCityClickListener {

    fun onCityClick(city: CityDto)
}
