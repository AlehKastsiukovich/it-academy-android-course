package by.training.task2.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.training.task2.R
import by.training.task2.databinding.ActivitySolverBinding
import by.training.task2.observer.DataObserver
import by.training.task2.observer.Observer
import by.training.task2.observer.Observers
import by.training.task2.operations.calculateArithmeticalMean
import by.training.task2.operations.calculateElementsSum
import by.training.task2.operations.calculateSeparationOperation

class SolverActivity : AppCompatActivity() {

    private var data: ArrayList<Int>? = null
    private lateinit var binding: ActivitySolverBinding
    private val observers = Observers<Observer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySolverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observers.add(DataObserver())

        getDataFromMainActivity()
    }

    private fun getDataFromMainActivity() {
        val intent = intent
        data = intent.getIntegerArrayListExtra(MainActivity.DATA_LIST)
        observers.notifyDataChanged(data)
    }

    fun onClick(view: View) {
        var result = "no result"
        val intent = Intent(this, MainActivity::class.java)

        when (view.id) {
            R.id.calculate_mean_button -> data?.let {
                result = calculateArithmeticalMean(it).toString()
                intent.putExtra(RESULT, result)
                setResult(RESULT_OK, intent)
                finish()
            }
            R.id.calculate_sum_button -> data?.let {
                result = calculateElementsSum(it).toString()
                intent.putExtra(RESULT, result)
                setResult(RESULT_OK, intent)
                finish()
            }
            R.id.separation_calculate_button -> data?.let {
                result = calculateSeparationOperation(it).toString()
                intent.putExtra(RESULT, result)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    companion object {
        const val RESULT = "result"
    }
}
