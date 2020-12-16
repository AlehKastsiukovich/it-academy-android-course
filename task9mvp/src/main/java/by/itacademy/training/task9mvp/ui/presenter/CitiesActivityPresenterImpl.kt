package by.itacademy.training.task9mvp.ui.presenter

import by.itacademy.training.task9mvp.model.dto.db.CityDto
import by.itacademy.training.task9mvp.model.repository.CitiesRepository
import javax.inject.Inject

class CitiesActivityPresenterImpl @Inject constructor(
    private val citiesRepository: CitiesRepository
) : CitiesActivityPresenter {

    override fun onDataLoading() {
        TODO("Not yet implemented")
    }

    override fun onErrorData() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(cityList: List<CityDto>) {
        TODO("Not yet implemented")
    }

    override fun provideDataFromDatabase() {
        TODO("Not yet implemented")
    }
}
