package by.itacademy.training.task9mvp.ui.view

import by.itacademy.training.task9mvp.model.domain.City

interface CityActivityView {

    fun showProgressBar()

    fun showViews()

    fun hideProgressBar()

    fun hideViews()

    fun showErrorMessage()

    fun showCities(cities: List<City>)
}
