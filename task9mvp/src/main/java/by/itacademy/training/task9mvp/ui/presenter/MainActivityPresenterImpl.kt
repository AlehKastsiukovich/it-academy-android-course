package by.itacademy.training.task9mvp.ui.presenter

import by.itacademy.training.task9mvp.model.entity.CurrentTemperature
import by.itacademy.training.task9mvp.model.entity.WeatherReport
import by.itacademy.training.task9mvp.model.repository.WeatherForecastRepository
import by.itacademy.training.task9mvp.ui.view.MainActivityView
import by.itacademy.training.task9mvp.util.CurrentTemperatureUnitListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityPresenterImpl @Inject constructor(
    private val mainActivityView: MainActivityView,
    private val weatherForecastRepository: WeatherForecastRepository,
    private val currentTemperatureUnitListener: CurrentTemperatureUnitListener
) : MainActivityPresenter {

    var currentCityName = "Minsk"

    override fun onSwitchTemperatureType(state: Boolean) {
        when (state) {
            true -> {
                currentTemperatureUnitListener.onFahrenheitTurnOn()
                provideDataFromApi()
            }
            false -> {
                currentTemperatureUnitListener.onCelsiusTurnOn()
                provideDataFromApi()
            }
        }
    }

    override fun onDataLoading() {
        mainActivityView.showProgressBar()
        mainActivityView.hideViews()
    }

    override fun onErrorData() {
        mainActivityView.showProgressBar()
        mainActivityView.showErrorMessage()
    }

    override fun onSuccess(weatherReport: WeatherReport) {
        mainActivityView.hideProgressBar()
        mainActivityView.showViews()
        mainActivityView.showWeatherReport(weatherReport)
    }

    override fun onOpenCityListButtonPress() {
        mainActivityView.startCityActivity()
    }

    override fun provideDataFromApi() {
        onDataLoading()
        val result = weatherForecastRepository
            .getWeatherForecastForDay("Minsk")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onSuccess(it)
                },
                {
                    onErrorData()
                }
            )
    }

    override fun getCurrentSwitcherState() =
        currentTemperatureUnitListener.getCurrentTemperatureUnitState()

    override fun getCurrentTemperature(currentTemperature: CurrentTemperature) =
        currentTemperatureUnitListener.getCurrentTemperature(currentTemperature)
}
