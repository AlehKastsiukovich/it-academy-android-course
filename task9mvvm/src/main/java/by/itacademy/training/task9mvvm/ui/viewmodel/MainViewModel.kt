package by.itacademy.training.task9mvvm.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import by.itacademy.training.task9mvvm.di.component.DaggerAppComponent
import by.itacademy.training.task9mvvm.model.repository.WeatherForecastRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var weatherForecastRepository: WeatherForecastRepositoryImpl

    init {
        DaggerAppComponent.create().inject(this)
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = weatherForecastRepository.getWeatherForecastForDay("Minsk")
            Log.d("TAG", result.toString())
        }
    }
}
