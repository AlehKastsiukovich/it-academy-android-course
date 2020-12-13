package by.itacademy.training.task9mvvm.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.task9mvvm.databinding.ActivityCitiesBinding
import by.itacademy.training.task9mvvm.ui.adapter.CityAdapter
import by.itacademy.training.task9mvvm.ui.viewmodel.CitiesViewModel

class CitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCitiesBinding
    private lateinit var model: CitiesViewModel
    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(CitiesViewModel::class.java)

        setUpRecyclerView()
        observeCitiesChanges()
        setAddCityButtonListener()
    }

    private fun observeCitiesChanges() {
        model.cities.observe(
            this,
            Observer {
                cityAdapter.addCities(it)
            }
        )
    }

    private fun setUpRecyclerView() {
        cityAdapter = CityAdapter()
        binding.citiesRecyclerView.apply {
            adapter = cityAdapter
            layoutManager = LinearLayoutManager(this@CitiesActivity)
        }
    }

    private fun setAddCityButtonListener() {
        binding.addCityFloatingButton.setOnClickListener {
            AddCityDialogFragment().apply { show(supportFragmentManager, "TAG") }
        }
    }
}
