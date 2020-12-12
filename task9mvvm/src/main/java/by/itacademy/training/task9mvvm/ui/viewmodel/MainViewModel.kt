package by.itacademy.training.task9mvvm.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.itacademy.training.task9mvvm.app.App
import by.itacademy.training.task9mvvm.model.entity.WeatherReport
import by.itacademy.training.task9mvvm.model.repository.WeatherForecastRepository
import by.itacademy.training.task9mvvm.util.Event
import by.itacademy.training.task9mvvm.util.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var weatherForecastRepository: WeatherForecastRepository

    private var _weatherReportData = MutableLiveData<Event<WeatherReport>>()
    val weatherReportData: LiveData<Event<WeatherReport>> = _weatherReportData

    init {
        (application as App).appComponent.inject(this)
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            _weatherReportData.value = (Event(Status.LOADING, null, null))
            try {
                val weatherReport = weatherForecastRepository.getWeatherForecastForDay("Minsk")
                _weatherReportData.value = ((Event(Status.SUCCESS, weatherReport, null)))
            } catch (exception: Exception) {
                _weatherReportData.value = (Event(Status.ERROR, null, exception.toString()))
            }
        }
    }
}
