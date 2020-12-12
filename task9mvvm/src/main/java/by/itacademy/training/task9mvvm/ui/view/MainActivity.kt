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
import by.itacademy.training.task9mvvm.model.entity.HourTemperature
import by.itacademy.training.task9mvvm.model.entity.WeatherReport
import by.itacademy.training.task9mvvm.ui.adapter.TemperatureAdapter
import by.itacademy.training.task9mvvm.ui.viewmodel.MainViewModel
import by.itacademy.training.task9mvvm.util.CurrentTemperatureUnitListener
import by.itacademy.training.task9mvvm.util.CurrentTemperatureUnitListenerImpl
import by.itacademy.training.task9mvvm.util.Event
import by.itacademy.training.task9mvvm.util.Status
import by.itacademy.training.task9mvvm.util.SupportSharedPreference
import by.itacademy.training.task9mvvm.util.SupportSharedPreferenceImpl
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var hourTemperatureAdapter: TemperatureAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var supportSharedPreference: SupportSharedPreference
    private lateinit var currentTemperatureUnitListener: CurrentTemperatureUnitListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportSharedPreference = SupportSharedPreferenceImpl(this)
        currentTemperatureUnitListener = CurrentTemperatureUnitListenerImpl(supportSharedPreference)

        setCurrentSwitcherState()
        injectDependencies()
        setUpViewModel()
        setUpRecyclerView()
        setDataToMainWindow()
        setSwitcherChangeListener()
    }

    private fun injectDependencies() {
        (application as App).appComponent.inject(this)
    }

    private fun setCurrentSwitcherState() {
        binding.temperatureUnitSwitcher.isChecked =
            currentTemperatureUnitListener.getCurrentTemperatureUnitState()
    }

    private fun setSwitcherChangeListener() {
        binding.temperatureUnitSwitcher.setOnCheckedChangeListener {
            _, isChecked ->
            when (isChecked) {
                true -> {
                    currentTemperatureUnitListener.onFahrenheitTurnOn()
                    mainViewModel.fetchData()
                }
                false -> {
                    currentTemperatureUnitListener.onCelsiusTurnOn()
                    mainViewModel.fetchData()
                }
            }
        }
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        hourTemperatureAdapter = TemperatureAdapter(currentTemperatureUnitListener)
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
                onLoadingData()
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
        binding.itemTemperatureTextView.text =
            currentTemperatureUnitListener.getCurrentTemperature(event.data?.currentTemperature)
        setDataToAdapter(event.data?.forecastDay?.list)
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

    private fun setDataToAdapter(list: List<HourTemperature>?) {
        list?.let {
            hourTemperatureAdapter.addElements(it)
        }
    }

    private fun setConditionImage(event: Event<WeatherReport>) {
        Glide.with(this)
            .load(event.data?.currentTemperature?.condition?.icon)
            .centerCrop()
            .into(binding.conditionImageView)
    }

    private fun hideViews() {
        binding.innerLayout.visibility = View.INVISIBLE
    }

    private fun showViews() {
        binding.innerLayout.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }
}
