package by.itacademy.training.task9mvvm.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.itacademy.training.task9mvvm.app.App
import by.itacademy.training.task9mvvm.model.dto.db.City
import by.itacademy.training.task9mvvm.model.repository.CitiesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CitiesViewModel(application: Application) : AndroidViewModel(application) {

//    private val dao = CitiesDatabase.getCityDatabase(application).citiesDao()
//    private val repository = CitiesRepositoryImpl(dao)
    @Inject lateinit var citiesRepository: CitiesRepository

    private var _cities: LiveData<List<City>> = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>>
        get() = _cities

    init {
        (application as App).appComponent.inject(this)
        _cities = citiesRepository.getAllCities()
    }

    fun addCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) { citiesRepository.insertCity(city) }
    }
}
