package by.itacademy.training.task9mvp.ui.presenter

import by.itacademy.training.task9mvp.model.entity.City
import by.itacademy.training.task9mvp.model.repository.CitiesRepository
import by.itacademy.training.task9mvp.ui.view.CityActivityView
import by.itacademy.training.task9mvp.util.SupportSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CitiesActivityPresenterImpl @Inject constructor(
    private val citiesRepository: CitiesRepository,
    private val citiesActivityView: CityActivityView,
    private val supportSharedPreference: SupportSharedPreference
) : CitiesActivityPresenter {

    override fun onDataLoading() {
        citiesActivityView.hideViews()
        citiesActivityView.showProgressBar()
    }

    override fun onErrorData() {
        citiesActivityView.showProgressBar()
        citiesActivityView.showErrorMessage()
    }

    override fun onSuccess(cityList: List<City>) {
        citiesActivityView.hideProgressBar()
        citiesActivityView.showViews()
        citiesActivityView.showCities(cityList)
    }

    override fun provideCitiesFromDatabase() {
        onDataLoading()
        val disposable = citiesRepository.getAllCities()
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

    override fun addCity(city: City) {
        val disposable = citiesRepository.insertCity(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}
