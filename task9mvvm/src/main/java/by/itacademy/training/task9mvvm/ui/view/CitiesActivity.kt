package by.itacademy.training.task9mvvm.ui.view

import android.content.Intent
import android.content.SharedPreferences
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
import by.itacademy.training.task9mvvm.util.SupportSharedPreference
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class CitiesActivity : AppCompatActivity(), CityAddListener, OnCityClickListener {

    @Inject lateinit var cityAdapter: CityAdapter
    @Inject lateinit var model: CitiesViewModel
    @Inject lateinit var supportSharedPreference: SupportSharedPreference

    private lateinit var binding: ActivityCitiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesBinding.inflate(layoutInflater)
        inject()
        setContentView(binding.root)

        setUpRecyclerView()
        observeCitiesChanges()
        setAddCityButtonListener()
    }

    private fun inject() {
        (application as App).appComponent
            .citiesActivityComponentBuilder()
            .with(this)
            .build()
            .inject(this)
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
        supportSharedPreference.setCurrentCity(city.name)
        startActivity(Intent(this, MainActivity::class.java))
    }

    companion object {
        private const val DIALOG_TAG = "dialogTag"
    }
}
