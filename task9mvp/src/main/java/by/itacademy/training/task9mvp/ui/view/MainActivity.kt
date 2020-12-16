package by.itacademy.training.task9mvp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.training.task9mvp.R
import by.itacademy.training.task9mvp.app.App
import by.itacademy.training.task9mvp.databinding.ActivityMainBinding
import by.itacademy.training.task9mvp.ui.adapter.TemperatureAdapter
import by.itacademy.training.task9mvp.ui.presenter.MainActivityPresenter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject lateinit var hourTemperatureAdapter: TemperatureAdapter
    @Inject lateinit var mainActivityPresenter: MainActivityPresenter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        injectDependencies()
        setContentView(binding.root)

//        chooseCurrentCity()
//        setCurrentSwitcherState()
//        setUpRecyclerView()
//        setDataToMainWindow()
//        setSwitcherChangeListener()
//        setUpCitiesManagementButton()
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

    override fun showCelsiusView() {
        TODO("Not yet implemented")
    }

    override fun showFahrenheitView() {
        TODO("Not yet implemented")
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

    override fun updateViews() {
        TODO("Not yet implemented")
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

//    private fun setCurrentSwitcherState() {
//        binding.temperatureUnitSwitcher.isChecked =
//            currentTemperatureUnitListener.getCurrentTemperatureUnitState()
//    }
//
//    private fun setSwitcherChangeListener() {
//        binding.temperatureUnitSwitcher.setOnCheckedChangeListener {
//            _, isChecked ->
//            when (isChecked) {
//                true -> {
//                    currentTemperatureUnitListener.onFahrenheitTurnOn()
//                    mainViewModel.fetchData()
//                }
//                false -> {
//                    currentTemperatureUnitListener.onCelsiusTurnOn()
//                    mainViewModel.fetchData()
//                }
//            }
//        }
//    }
//
//    private fun chooseCurrentCity() {
//        val cityName = intent?.extras?.getString(resources.getString(R.string.city_name_bundle))
//        cityName?.let {
//            mainViewModel.cityName = it
//            mainViewModel.fetchData()
//        }
//    }
//
//    private fun setUpViewModel() {
//        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//    }
//
//    private fun setUpRecyclerView() {
//        binding.recyclerView.apply {
//            adapter = hourTemperatureAdapter
//            layoutManager = LinearLayoutManager(
//                this@MainActivity,
//                LinearLayoutManager.HORIZONTAL,
//                false
//            )
//        }
//    }
//
//    private fun setDataToMainWindow() {
//        mainViewModel.weatherReportData.observe(
//            this,
//            Observer {
//                renderDataView(it)
//            }
//        )
//    }
//
//    private fun renderDataView(event: Event<WeatherReport>) {
//        when (event.status) {
//            Status.LOADING -> onLoadingData()
//            Status.SUCCESS -> onSuccessDataLoading(event)
//            Status.ERROR -> onErrorDataLoading()
//        }
//    }
//
//    private fun onSuccessDataLoading(event: Event<WeatherReport>) {
//        hideProgressBar()
//        setConditionImage(event)
//        locationDataToView(event)
//        binding.itemTemperatureTextView.text =
//            currentTemperatureUnitListener.getCurrentTemperature(event.data?.currentTemperature)
//        setDataToAdapter(event.data?.forecastDay?.list)
//        showViews()
//    }
//
//    private fun onLoadingData() {
//        hideViews()
//        showProgressBar()
//    }
//
//    private fun onErrorDataLoading() {
//        hideViews()
//        showProgressBar()
//        Snackbar.make(
//            binding.root,
//            resources.getString(R.string.weather_report_loading_error),
//            Snackbar.LENGTH_LONG
//        ).show()
//    }
//
//    private fun locationDataToView(event: Event<WeatherReport>) {
//        binding.cityNameTextView.text = event.data?.location?.name
//        binding.regionNameTextView.text = event.data?.location?.country
//    }
//
//    private fun setDataToAdapter(list: List<HourTemperature>?) {
//        list?.let {
//            hourTemperatureAdapter.addElements(it)
//        }
//    }
//
//    private fun setConditionImage(event: Event<WeatherReport>) {
//        Glide.with(this)
//            .load(event.data?.currentTemperature?.condition?.icon)
//            .centerCrop()
//            .into(binding.conditionImageView)
//    }
//
//    private fun hideViews() {
//        binding.innerLayout.visibility = View.INVISIBLE
//    }
//
//    private fun showViews() {
//        binding.innerLayout.visibility = View.VISIBLE
//    }
//
//    private fun showProgressBar() {
//        binding.progressBar.visibility = View.VISIBLE
//    }
//
//    private fun hideProgressBar() {
//        binding.progressBar.visibility = View.INVISIBLE
//    }
}
