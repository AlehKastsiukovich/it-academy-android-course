package by.itacademy.training.task4p2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.training.task4p2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var switchOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNotificationSwitch()
        setUpColorMixerCustomView()
    }

    private fun setUpNotificationSwitch() {
        binding.notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            switchOn = isChecked
        }
    }

    private fun setUpColorMixerCustomView() {
        binding.colorMixerCustomView.setColorTouchListener(object : ColorTouchListener {
            override fun onTouchColorMixer(x: Float, y: Float, color: Int?) {
                when (switchOn) {
                    true -> {
                        val snackBar = Snackbar.make(
                            binding.root,
                            "x: $x; y: $y",
                            Snackbar.LENGTH_SHORT
                        )
                        color?.let { snackBar.setBackgroundTint(it) }
                        snackBar.show()
                    }
                    false -> {
                        Toast.makeText(
                            this@MainActivity,
                            "x: $x; y: $y",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }
}
