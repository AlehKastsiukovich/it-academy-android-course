package by.itacademy.training.task9mvp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.task9mvp.R
import by.itacademy.training.task9mvp.app.App
import by.itacademy.training.task9mvp.databinding.ActivityCitiesBinding
import by.itacademy.training.task9mvp.model.dto.db.CityDto
import by.itacademy.training.task9mvp.ui.adapter.CityAdapter
import by.itacademy.training.task9mvp.ui.adapter.OnCityClickListener
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class CitiesActivity : AppCompatActivity(), CityAddListener, OnCityClickListener {

    @Inject lateinit var cityAdapter: CityAdapter

    private lateinit var binding: ActivityCitiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesBinding.inflate(layoutInflater)
        inject()
        setContentView(binding.root)

//        setUpRecyclerView()
//        observeCitiesChanges()
//        setAddCityButtonListener()
    }

    private fun inject() {
        (application as App).appComponent
            .citiesActivityComponentBuilder()
            .with(this)
            .build()
            .inject(this)
    }

    private fun observeCitiesChanges() {
//        onLoading()
//        try {
//            model.cities.observe(this, Observer { cityAdapter.addCities(it) })
//            onSuccess()
//        } catch (e: Exception) {
//            onError()
//        }
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

    override fun onCityAdd(city: CityDto) {
//        model.addCity(city)
    }

    override fun onCityClick(city: CityDto) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(resources.getString(R.string.city_name_bundle), city.name)
        startActivity(intent)
    }

    companion object {
        private const val DIALOG_TAG = "dialogTag"
    }
}
