package by.itacademy.training.task9mvp.ui.presenter

interface MainActivityPresenter {

    fun onSwitchTemperatureType()

    fun onDataLoading()

    fun onErrorData()

    fun onSuccess()

    fun onOpenCityListButtonPress()

    fun getDataFromApi()
}
