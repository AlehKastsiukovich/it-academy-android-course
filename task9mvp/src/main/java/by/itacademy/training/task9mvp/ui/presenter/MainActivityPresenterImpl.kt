package by.itacademy.training.task9mvp.ui.presenter

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

    override fun onSwitchTemperatureType() {
        mainActivityView.updateViews()
    }

    override fun onDataLoading() {
        mainActivityView.showProgressBar()
        mainActivityView.hideViews()
    }

    override fun onErrorData() {
        mainActivityView.showProgressBar()
        mainActivityView.showErrorMessage()
    }

    override fun onSuccess() {
        mainActivityView.hideProgressBar()
        mainActivityView.showViews()
    }

    override fun onOpenCityListButtonPress() {
        mainActivityView.startCityActivity()
    }

    override fun getDataFromApi() {
        onDataLoading()
        val result = weatherForecastRepository
            .getWeatherForecastForDay("Minsk")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onSuccess()
                },
                {
                    onErrorData()
                }
            )
    }
}
