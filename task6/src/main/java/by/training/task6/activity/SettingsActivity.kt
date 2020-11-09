package by.training.task6.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.training.task6.R
import by.training.task6.adapter.StorageType
import by.training.task6.databinding.ActivitySettingsBinding
import by.training.task6.util.StorageUtil
import kotlinx.android.synthetic.main.activity_settings.saveToExternalStorageSwitch

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sharedPreferences =
            getSharedPreferences(getString(R.string.application_preference), MODE_PRIVATE)

        getCurrentSwitchState()

        saveToExternalStorageSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                StorageUtil.saveStorageType(StorageType.EXTERNAL, sharedPreferences)
            } else {
                StorageUtil.saveStorageType(StorageType.INTERNAL, sharedPreferences)
            }
        }
    }

    private fun getCurrentSwitchState() {
        binding.saveToExternalStorageSwitch.isChecked =
            StorageUtil.getStorageType(sharedPreferences) == StorageType.EXTERNAL
    }
}
