package by.itacademy.training.task9mvp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.task9mvp.model.entity.WeatherReport
import by.itacademy.training.task9mvp.util.Event
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

//    @Inject lateinit var weatherForecastRepository: WeatherForecastRepository

    private var _weatherReportData = MutableLiveData<Event<WeatherReport>>()
    val weatherReportData: LiveData<Event<WeatherReport>> = _weatherReportData
    var cityName = DEFAULT_CITY

    init {
//        (application as App).appComponent.inject(this)
//        fetchData()
    }

    fun fetchData() {
//        viewModelScope.launch {
//            _weatherReportData.value = Event(Status.LOADING, null, null)
//            try {
//                val weatherReport = weatherForecastRepository.getWeatherForecastForDay(cityName)
//                _weatherReportData.value = Event(Status.SUCCESS, weatherReport, null)
//            } catch (exception: Exception) {
//                _weatherReportData.value = Event(Status.ERROR, null, exception.toString())
//            }
//        }
    }

    companion object {
        private const val DEFAULT_CITY = "Minsk"
    }
}
