package by.itacademy.training.task9mvvm.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.task9mvvm.databinding.ActivityMainBinding
import by.itacademy.training.task9mvvm.ui.adapter.TemperatureAdapter
import by.itacademy.training.task9mvvm.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var hourTemperatureAdapter: TemperatureAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpRecyclerView()
        setDataToRecyclerView()
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        hourTemperatureAdapter = TemperatureAdapter()

        binding.recyclerView.apply {
            adapter = hourTemperatureAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity
            )
        }
    }

    private fun setDataToRecyclerView() {
        Log.d("TAG", "in setup: ")
        mainViewModel.weatherReportData.observe(
            this,
            Observer { event ->
                event.data?.forecastDay?.list?.let {
                    Log.d("TAG", "in setData size : " + it.size.toString())
                    hourTemperatureAdapter.addElements(it)
                }
            }
        )
    }
}
