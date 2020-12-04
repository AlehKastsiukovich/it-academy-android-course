package by.itacademy.training.task8.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.training.task8.R
import by.itacademy.training.task8.databinding.ActivityMultithreadingPreferenceSettingsBinding
import by.itacademy.training.task8.util.MultithreadingType

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

        setCurrentRadioGroupState()
    }

    private fun setCurrentRadioGroupState() {
        val multithreadingType =
            (application as App).sharedPreferences.getCurrentMultithreadingType()

        binding.radioGroup.apply {
            when (multithreadingType) {
                MultithreadingType.RX -> check(R.id.rxRadioButton)
                MultithreadingType.CALLABLE_FUTURE -> check(R.id.callableRadioButton)
                MultithreadingType.HANDLER_THREAD -> check(R.id.handlerRadioButton)
            }
        }
    }

    private fun onChange(checkedId: Int) {
        when (checkedId) {
            R.id.rxRadioButton ->
                (application as App)
                    .sharedPreferences
                    .setCurrentMultithreadingType(MultithreadingType.RX)
            R.id.callableRadioButton ->
                (application as App)
                    .sharedPreferences
                    .setCurrentMultithreadingType(MultithreadingType.CALLABLE_FUTURE)
            R.id.handlerRadioButton ->
                (application as App)
                    .sharedPreferences
                    .setCurrentMultithreadingType(MultithreadingType.HANDLER_THREAD)
        }
    }
}
