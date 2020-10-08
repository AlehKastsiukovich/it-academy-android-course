package by.training.task2.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.training.task2.R
import by.training.task2.databinding.ActivitySolverBinding
import by.training.task2.observer.Observers
import by.training.task2.operations.calculateArithmeticalMean
import by.training.task2.operations.calculateElementsSum
import by.training.task2.operations.calculateSeparationOperation

class SolverActivity : AppCompatActivity() {

    private var data: ArrayList<Int>? = null
    private lateinit var binding: ActivitySolverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySolverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromMainActivity()
    }

    private fun getDataFromMainActivity() {
        val intent = intent
        data = intent.getIntegerArrayListExtra(MainActivity.DATA_LIST)
    }

    fun onClick(view: View) {
        var result: String = ""
        when (view.id) {
            R.id.calculate_mean_button -> data?.let {
                result = calculateArithmeticalMean(it).toString()
            }
            R.id.calculate_sum_button -> data?.let {
                result = calculateElementsSum(it).toString()
            }
            R.id.separation_calculate_button -> data?.let {
                result = calculateSeparationOperation(it).toString()
            }
        }

        Observers.notifyObservers(result)
    }
}
