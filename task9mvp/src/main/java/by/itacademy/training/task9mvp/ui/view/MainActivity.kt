package by.itacademy.training.task9mvp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.task9mvp.R
import by.itacademy.training.task9mvp.app.App
import by.itacademy.training.task9mvp.databinding.ActivityMainBinding
import by.itacademy.training.task9mvp.model.entity.HourTemperature
import by.itacademy.training.task9mvp.model.entity.WeatherReport
import by.itacademy.training.task9mvp.ui.adapter.TemperatureAdapter
import by.itacademy.training.task9mvp.ui.presenter.MainActivityPresenter
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject lateinit var hourTemperatureAdapter: TemperatureAdapter
    @Inject lateinit var presenter: MainActivityPresenter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        injectDependencies()
        setContentView(binding.root)

//        chooseCurrentCity()
        setCurrentSwitcherState()
        setUpRecyclerView()
        setDataToMainWindow()
        setSwitcherChangeListener()
        setUpCitiesManagementButton()
        setUpCitiesManagementButton()
    }

    private fun setUpCitiesManagementButton() {
        binding.citiesManagementButton.setOnClickListener {
            startActivity(Intent(this, CitiesActivity::class.java))
        }
    }

    private fun injectDependencies() {
        (application as App)
            .appComponent
            .activityComponentBuilder()
            .with(this)
            .build()
            .inject(this)
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun showViews() {
        binding.innerLayout.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun hideViews() {
        binding.innerLayout.visibility = View.INVISIBLE
    }

    override fun showErrorMessage() {
        Snackbar.make(
            binding.root,
            resources.getString(R.string.weather_report_loading_error),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun startCityActivity() {
        startActivity(Intent(this, CitiesActivity::class.java))
    }

    private fun setCurrentSwitcherState() {
        binding.temperatureUnitSwitcher.isChecked = presenter.getCurrentSwitcherState()
    }

    private fun setSwitcherChangeListener() {
        binding.temperatureUnitSwitcher.setOnCheckedChangeListener {
            _, isChecked ->
            presenter.onSwitchTemperatureType(isChecked)
        }
    }

    private fun chooseCurrentCity() {
        val cityName = intent?.extras?.getString(resources.getString(R.string.city_name_bundle))
        cityName?.let {
            mainViewModel.cityName = it
            mainViewModel.fetchData()
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            adapter = hourTemperatureAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun setDataToMainWindow() {
        presenter.provideDataFromApi()
    }

    override fun showWeatherReport(weatherReport: WeatherReport) {
        hideProgressBar()
        setConditionImage(weatherReport)
        locationDataToView(weatherReport)
        binding.itemTemperatureTextView.text =
            presenter.getCurrentTemperature(weatherReport.currentTemperature)
        setDataToAdapter(weatherReport.forecastDay.list)
        showViews()
    }

    private fun locationDataToView(weatherReport: WeatherReport) {
        binding.cityNameTextView.text = weatherReport.location.name
        binding.regionNameTextView.text = weatherReport.location.country
    }

    private fun setDataToAdapter(list: List<HourTemperature>?) {
        list?.let {
            hourTemperatureAdapter.addElements(it)
        }
    }

    private fun setConditionImage(weatherReport: WeatherReport) {
        Glide.with(this)
            .load(weatherReport.currentTemperature.condition.icon)
            .centerCrop()
            .into(binding.conditionImageView)
    }
}
