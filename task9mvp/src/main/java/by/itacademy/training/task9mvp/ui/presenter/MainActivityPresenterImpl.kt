package by.itacademy.training.task9mvp.ui.presenter

import by.itacademy.training.task9mvp.model.domain.CurrentTemperature
import by.itacademy.training.task9mvp.model.domain.WeatherReport
import by.itacademy.training.task9mvp.model.repository.WeatherForecastRepository
import by.itacademy.training.task9mvp.ui.view.MainActivityView
import by.itacademy.training.task9mvp.util.SupportSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityPresenterImpl @Inject constructor(
    private val mainActivityView: MainActivityView,
    private val weatherForecastRepository: WeatherForecastRepository,
    private val currentTemperatureUnitListener: CurrentTemperatureUnitListener,
    private val supportSharedPreference: SupportSharedPreference,
    private val compositeDisposable: CompositeDisposable

) : MainActivityPresenter {

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
        val disposable = weatherForecastRepository
            .getWeatherForecastForDay(getCurrentCity())
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
        compositeDisposable.add(disposable)
    }

    override fun getCurrentSwitcherState() =
        currentTemperatureUnitListener.getCurrentTemperatureUnitState()

    override fun getCurrentTemperature(currentTemperature: CurrentTemperature) =
        currentTemperatureUnitListener.getCurrentTemperature(currentTemperature)

    override fun onActivityDestroy() {
        compositeDisposable.dispose()
    }

    private fun getCurrentCity() = supportSharedPreference.getCurrentCity() ?: DEFAULT_CITY

    companion object {
        private const val DEFAULT_CITY = "Minsk"
    }
}
