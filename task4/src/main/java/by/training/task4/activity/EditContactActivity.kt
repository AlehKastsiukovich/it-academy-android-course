package by.training.task4.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.training.task4.R

class EditContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
