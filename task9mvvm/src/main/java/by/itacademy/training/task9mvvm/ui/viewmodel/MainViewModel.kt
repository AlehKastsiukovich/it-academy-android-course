package by.itacademy.training.task9mvvm.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.itacademy.training.task9mvvm.di.component.DaggerAppComponent
import by.itacademy.training.task9mvvm.model.entity.WeatherReport
import by.itacademy.training.task9mvvm.model.repository.WeatherForecastRepository
import by.itacademy.training.task9mvvm.util.DTOMapper
import by.itacademy.training.task9mvvm.util.Event
import by.itacademy.training.task9mvvm.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(application: Application) : AndroidViewModel(application) {

    @Inject lateinit var weatherForecastRepository: WeatherForecastRepository
    @Inject lateinit var dtoMapper: DTOMapper

    private var _weatherReportData = MutableLiveData<Event<WeatherReport>>()
    val weatherReportData: LiveData<Event<WeatherReport>> = _weatherReportData

    init {
        DaggerAppComponent.create().inject(this)
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.Main) {
            _weatherReportData.value = (Event(Status.LOADING, null, null))
            Log.d("TAG", "status start: " + _weatherReportData.value?.status?.name)
            try {
                val result = weatherForecastRepository.getWeatherForecastForDay("Minsk")
                val weatherReport = dtoMapper.invoke(result)
                Log.d("TAG", "weatherreport: " + weatherReport.toString())
                _weatherReportData.value = ((Event(Status.SUCCESS, weatherReport, null)))
                Log.d("TAG", "status success: " + _weatherReportData.value?.data.toString())
            } catch (exception: Exception) {
                _weatherReportData.postValue(Event(Status.ERROR, null, exception.message))
            }
        }
    }
}
