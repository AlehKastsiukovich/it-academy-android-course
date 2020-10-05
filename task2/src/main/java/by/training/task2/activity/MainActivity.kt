package by.training.task2.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.training.task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendDataButton.setOnClickListener {
            sendRandomIntList()
        }
    }

    private fun sendRandomIntList() {
        val shuffledList: ArrayList<Int> = (MIN_VALUE..MAX_VALUE).shuffled() as ArrayList<Int>

        val intent = Intent(this, SolverActivity::class.java)
        intent.putIntegerArrayListExtra(DATA_LIST, shuffledList)
        startActivityForResult(intent, SOLVER_ACTIVITY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                SOLVER_ACTIVITY_REQUEST_CODE ->
                    binding.resultTextView.text =
                        data.getStringExtra(SolverActivity.RESULT).toString()
            }
        }
    }

    companion object {
        const val DATA_LIST = "dataList"
        const val MIN_VALUE = -100
        const val MAX_VALUE = 99
        const val SOLVER_ACTIVITY_REQUEST_CODE = 9999
    }
}
