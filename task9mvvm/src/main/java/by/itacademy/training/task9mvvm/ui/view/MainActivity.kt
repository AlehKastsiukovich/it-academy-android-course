package by.itacademy.training.task9mvvm.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.task9mvvm.app.App
import by.itacademy.training.task9mvvm.databinding.ActivityMainBinding
import by.itacademy.training.task9mvvm.ui.adapter.TemperatureAdapter
import by.itacademy.training.task9mvvm.ui.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var hourTemperatureAdapter: TemperatureAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        injectDependencies()
        setUpViewModel()
        setUpRecyclerView()
        setDataToRecyclerView()
    }

    private fun injectDependencies() {
        (application as App).appComponent.inject(this)
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

    private fun setDataToRecyclerView() {
        mainViewModel.weatherReportData.observe(
            this,
            Observer { event ->
                event.data?.forecastDay?.list?.let {
                    hourTemperatureAdapter.addElements(it)
                }
            }
        )
    }
}
