package by.itacademy.training.task8.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.training.task8.R
import by.itacademy.training.task8.databinding.ActivityMultithreadingPreferenceSettingsBinding
import by.itacademy.training.task8.util.MultithreadingTypes

class MultithreadingPreferenceSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMultithreadingPreferenceSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultithreadingPreferenceSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioGroup.setOnCheckedChangeListener {
            _, checkedId ->
            onChange(checkedId)
        }
    }

    private fun setCurrentRadioGroupState() {
        val x = (application as App).sharedPreferences.getCurrentMultithreadingType()
    }

    private fun onChange(checkedId: Int) {
        when (checkedId) {
            R.id.rxRadioButton ->
                (application as App)
                    .sharedPreferences
                    .setCurrentMultithreadingType(MultithreadingTypes.RX)
            R.id.callableRadioButton ->
                (application as App)
                    .sharedPreferences
                    .setCurrentMultithreadingType(MultithreadingTypes.CALLABLE_FUTURE)
            R.id.handlerRadioButton ->
                (application as App)
                    .sharedPreferences
                    .setCurrentMultithreadingType(MultithreadingTypes.HANDLER_THREAD)
        }
    }
}
