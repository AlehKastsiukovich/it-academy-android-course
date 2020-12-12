package by.itacademy.training.task9mvvm.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.task9mvvm.R
import by.itacademy.training.task9mvvm.app.App
import by.itacademy.training.task9mvvm.databinding.ActivityMainBinding
import by.itacademy.training.task9mvvm.model.entity.WeatherReport
import by.itacademy.training.task9mvvm.ui.adapter.TemperatureAdapter
import by.itacademy.training.task9mvvm.ui.viewmodel.MainViewModel
import by.itacademy.training.task9mvvm.util.Event
import by.itacademy.training.task9mvvm.util.Status
import by.itacademy.training.task9mvvm.util.SupportSharedPreference
import by.itacademy.training.task9mvvm.util.SupportSharedPreferenceImpl
import by.itacademy.training.task9mvvm.util.TemperatureUnit
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var hourTemperatureAdapter: TemperatureAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var supportSharedPreference: SupportSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportSharedPreference = SupportSharedPreferenceImpl(this)

        injectDependencies()
        setUpViewModel()
        setUpRecyclerView()
        setDataToMainWindow()
        setSwitcherChangeListener()
    }

    private fun injectDependencies() {
        (application as App).appComponent.inject(this)
    }

    private fun setSwitcherChangeListener() {
        binding.temperatureUnitSwitcher.setOnCheckedChangeListener {
            _, isChecked ->
            when (isChecked) {
                true -> supportSharedPreference.setCurrentTemperatureUnit(TemperatureUnit.FAHRENHEIT)
                false -> supportSharedPreference.setCurrentTemperatureUnit(TemperatureUnit.CELSIUS)
            }
        }
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        hourTemperatureAdapter = TemperatureAdapter()

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
        mainViewModel.weatherReportData.observe(
            this,
            Observer {
                renderDataView(it)
            }
        )
    }

    private fun renderDataView(event: Event<WeatherReport>) {
        when (event.status) {
            Status.LOADING -> {
                onErrorDataLoading()
            }
            Status.SUCCESS -> {
                onSuccessDataLoading(event)
            }
            Status.ERROR -> {
                onErrorDataLoading()
            }
        }
    }

    private fun onSuccessDataLoading(event: Event<WeatherReport>) {
        hideProgressBar()
        setConditionImage(event)
        binding.itemTemperatureTextView.text = event.data?.currentTemperature?.celsiusTemperature.toString()
        event.data?.forecastDay?.list?.let {
            hourTemperatureAdapter.addElements(it)
        }
        showViews()
    }

    private fun onLoadingData() {
        hideViews()
        showProgressBar()
    }

    private fun onErrorDataLoading() {
        hideViews()
        showProgressBar()
        Snackbar.make(
            binding.root,
            resources.getString(R.string.weather_report_loading_error),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setConditionImage(event: Event<WeatherReport>) {
        Glide.with(this)
            .load(event.data?.currentTemperature?.condition?.icon)
            .centerCrop()
            .into(binding.conditionImageView)
    }

    private fun hideViews() {
        with(binding) {
            itemTemperatureTextView.visibility = View.INVISIBLE
            cityNameTextView.visibility = View.INVISIBLE
            regionNameTextView.visibility = View.INVISIBLE
            recyclerView.visibility = View.INVISIBLE
            conditionImageView.visibility = View.INVISIBLE
        }
    }

    private fun showViews() {
        with(binding) {
            itemTemperatureTextView.visibility = View.VISIBLE
            cityNameTextView.visibility = View.VISIBLE
            regionNameTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.VISIBLE
            conditionImageView.visibility = View.VISIBLE
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }
}
