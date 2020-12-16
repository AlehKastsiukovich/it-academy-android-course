package by.itacademy.training.task9mvp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.task9mvp.model.dto.db.CityDto
import javax.inject.Inject

class CitiesViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

//    @Inject lateinit var citiesRepository: CitiesRepository

    private var _cities: LiveData<List<CityDto>> = MutableLiveData<List<CityDto>>()
    val cities: LiveData<List<CityDto>>
        get() = _cities

    init {
//        (application as App).appComponent.inject(this)
//        _cities = citiesRepository.getAllCities()
    }

    fun addCity(city: CityDto) {
//        viewModelScope.launch(Dispatchers.IO) { citiesRepository.insertCity(city) }
    }
}
