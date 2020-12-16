package by.itacademy.training.task9mvp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.itacademy.training.task9mvp.app.App
import by.itacademy.training.task9mvp.model.dto.db.City
import by.itacademy.training.task9mvp.model.repository.CitiesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CitiesViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

//    @Inject lateinit var citiesRepository: CitiesRepository

    private var _cities: LiveData<List<City>> = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>>
        get() = _cities

    init {
//        (application as App).appComponent.inject(this)
//        _cities = citiesRepository.getAllCities()
    }

    fun addCity(city: City) {
//        viewModelScope.launch(Dispatchers.IO) { citiesRepository.insertCity(city) }
    }
}
