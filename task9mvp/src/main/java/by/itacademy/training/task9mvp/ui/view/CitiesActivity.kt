package by.itacademy.training.task9mvp.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.task9mvp.R
import by.itacademy.training.task9mvp.app.App
import by.itacademy.training.task9mvp.databinding.ActivityCitiesBinding
import by.itacademy.training.task9mvp.model.entity.City
import by.itacademy.training.task9mvp.ui.adapter.CityAdapter
import by.itacademy.training.task9mvp.ui.adapter.OnCityClickListener
import by.itacademy.training.task9mvp.ui.presenter.CitiesActivityPresenter
import by.itacademy.training.task9mvp.util.SupportSharedPreference
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class CitiesActivity : AppCompatActivity(), CityAddListener, OnCityClickListener, CityActivityView {

    @Inject lateinit var cityAdapter: CityAdapter
    @Inject lateinit var presenter: CitiesActivityPresenter
    @Inject lateinit var supportSharedPreference: SupportSharedPreference

    private lateinit var binding: ActivityCitiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesBinding.inflate(layoutInflater)
        inject()
        setContentView(binding.root)

        setUpRecyclerView()
        renderData()
        setAddCityButtonListener()
    }

    private fun inject() {
        (application as App).appComponent
            .citiesActivityComponentBuilder()
            .with(this)
            .build()
            .inject(this)
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun showViews() {
        binding.container.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun hideViews() {
        binding.container.visibility = View.INVISIBLE
    }

    override fun showErrorMessage() {
        Snackbar.make(
            binding.root,
            resources.getString(R.string.weather_report_loading_error),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showCities(cities: List<City>) {
        cityAdapter.addCities(cities)
    }

    private fun renderData() {
        presenter.provideCitiesFromDatabase()
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
        presenter.addCity(city)
    }

    override fun onCityClick(city: City) {
        supportSharedPreference.setCurrentCity(city.cityName)
        finish()
    }

    companion object {
        private const val DIALOG_TAG = "dialogTag"
    }
}
