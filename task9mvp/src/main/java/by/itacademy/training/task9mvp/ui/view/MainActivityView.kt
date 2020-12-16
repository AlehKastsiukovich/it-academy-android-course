package by.itacademy.training.task9mvp.ui.view

import by.itacademy.training.task9mvp.model.domain.WeatherReport

interface MainActivityView {

    fun showProgressBar()

    fun showViews()

    fun hideProgressBar()

    fun hideViews()

    fun showErrorMessage()

    fun startCityActivity()

    fun showWeatherReport(weatherReport: WeatherReport)
}
