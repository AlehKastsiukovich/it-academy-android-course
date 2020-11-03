package by.training.task6.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.training.task6.R
import kotlinx.android.synthetic.main.activity_settings.saveToExternalStorageSwitch

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sharedPreferences = getSharedPreferences(
            getString(R.string.application_preference), MODE_PRIVATE
        )

        getCurrentSwitchState()

        saveToExternalStorageSwitch.setOnCheckedChangeListener { _, isChecked ->
            preference.edit().putBoolean(getString(R.string.storage_option), isChecked).apply()
        }
    }

    private fun getCurrentSwitchState() {
        val currentState = getSharedPreferences(
            getString(R.string.application_preference), MODE_PRIVATE
        ).getBoolean(getString(R.string.storage_option), false)

        saveToExternalStorageSwitch.isChecked = currentState
    }
}
