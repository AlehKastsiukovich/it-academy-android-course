package by.itacademy.training.task8.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.training.task8.R
import by.itacademy.training.task8.databinding.ActivityMultithreadingPreferenceSettingsBinding

class MultithreadingPreferenceSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMultithreadingPreferenceSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultithreadingPreferenceSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioGroup.setOnCheckedChangeListener {
                _, checkedId ->
            when (checkedId) {
                R.id.rxRadioButton -> getSharedPreferences()
            }
        }
    }
}
