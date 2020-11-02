package by.training.task6.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.training.task6.R
import kotlinx.android.synthetic.main.activity_text_editor.editTextInputLayout
import kotlinx.android.synthetic.main.activity_text_editor.locationEditText

class TextEditorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_editor)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fileName = getFileName()
        readFileContent(fileName)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_text_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveFile) {
            val textToSave = locationEditText.text.toString()
            val file = locationEditText.hint.toString()
            openFileOutput(file, MODE_PRIVATE).use {
                it.write(textToSave.toByteArray())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getFileName(): String {
        val fileName = intent.extras?.getString(FILE_NAME_EXTRAS) ?: ""
        editTextInputLayout.hint = fileName
        return fileName
    }

    private fun readFileContent(fileName: String) {
        var text = ""
        openFileInput(fileName).bufferedReader().useLines { lines ->
            text = lines.joinToString {
                it
            }
        }
        locationEditText.setText(text)
    }
}
