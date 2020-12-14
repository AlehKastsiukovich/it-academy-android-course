package by.itacademy.training.task9mvvm.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.task9mvvm.R
import by.itacademy.training.task9mvvm.app.App
import by.itacademy.training.task9mvvm.databinding.ActivityCitiesBinding
import by.itacademy.training.task9mvvm.model.dto.db.City
import by.itacademy.training.task9mvvm.ui.adapter.CityAdapter
import by.itacademy.training.task9mvvm.ui.adapter.OnCityClickListener
import by.itacademy.training.task9mvvm.ui.viewmodel.CitiesViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class CitiesActivity : AppCompatActivity(), CityAddListener, OnCityClickListener {

    private lateinit var cityAdapter: CityAdapter
    private lateinit var binding: ActivityCitiesBinding
    private lateinit var model: CitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesBinding.inflate(layoutInflater)
        (application as App).appComponent.inject(this)
        setContentView(binding.root)

        setUpViewModel()
        setUpRecyclerView()
        observeCitiesChanges()
        setAddCityButtonListener()
    }

    private fun setUpViewModel() {
        model = ViewModelProvider(this).get(CitiesViewModel::class.java)
    }

    private fun observeCitiesChanges() {
        onLoading()
        try {
            model.cities.observe(this, Observer { cityAdapter.addCities(it) })
            onSuccess()
        } catch (e: Exception) {
            onError()
        }
    }

    private fun onSuccess() {
        with(binding) {
            container.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }
    }

    private fun onLoading() {
        with(binding) {
            container.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun onError() {
        with(binding) {
            container.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }
        showErrorMessage()
    }

    private fun showErrorMessage() {
        Snackbar.make(
            binding.root,
            resources.getString(R.string.weather_report_loading_error),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setUpRecyclerView() {
        cityAdapter = CityAdapter(this)
        binding.citiesRecyclerView.apply {
            adapter = cityAdapter
            layoutManager = LinearLayoutManager(this@CitiesActivity)
        }
    }

    private fun setAddCityButtonListener() {
        binding.addCityFloatingButton.setOnClickListener {
            AddCityDialogFragment(this).apply { show(supportFragmentManager, DIALOG_TAG) }
        }
    }

    override fun onCityAdd(city: City) {
        model.addCity(city)
    }

    override fun onCityClick(city: City) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(resources.getString(R.string.city_name_bundle), city.name)
        startActivity(intent)
    }

    companion object {
        private const val DIALOG_TAG = "dialogTag"
    }
}
