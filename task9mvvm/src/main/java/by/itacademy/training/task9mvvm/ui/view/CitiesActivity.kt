package by.itacademy.training.task9mvvm.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.training.task9mvvm.databinding.ActivityCitiesBinding

class CitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCitiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAddButtonListener()
    }

    private fun setAddButtonListener() {
        binding.addCityFloatingButton.setOnClickListener {
            val fragment = AddCityDialogFragment()
            fragment.show(supportFragmentManager, "TAG")
        }
    }
}
