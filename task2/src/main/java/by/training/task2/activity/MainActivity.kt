package by.training.task2.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.training.task2.databinding.ActivityMainBinding
import by.training.task2.observer.Observer
import by.training.task2.observer.Observers

class MainActivity : AppCompatActivity(), Observer {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Observers.addObserver(this)

        binding.sendDataButton.setOnClickListener {
            sendRandomIntList()
        }
    }

    private fun sendRandomIntList() {
        val shuffledList: ArrayList<Int> = (MIN_VALUE..MAX_VALUE).shuffled() as ArrayList<Int>

        val intent = Intent(this, SolverActivity::class.java)
        intent.putIntegerArrayListExtra(DATA_LIST, shuffledList)
        startActivity(intent)
    }

    override fun notifyDataChanged(data: String) {
        binding.resultTextView.text = data
    }

    companion object {
        const val DATA_LIST = "dataList"
        const val MIN_VALUE = -100
        const val MAX_VALUE = 99
    }
}
