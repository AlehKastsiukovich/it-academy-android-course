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

        binding.notificationSwitch.setOnCheckedChangeListener {
            _, isChecked ->
            switchOn = isChecked
        }

        binding.cvColorMixer.setListener(object : ColorMixerTouchListener {
            override fun onTouchColorMixer(x: Float, y: Float, color: Int?) {
                if (switchOn) {
                    val sbView =
                        Snackbar.make(binding.root, "Нажаты координаты[$x; $y]", Snackbar.LENGTH_SHORT)
                    color?.let { sbView.setBackgroundTint(it) }
                    sbView.show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Нажаты координаты[$x; $y]",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}
