package by.training.task6.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import by.training.task6.R
import kotlinx.android.synthetic.main.activity_text_editor.editTextInputLayout
import kotlinx.android.synthetic.main.activity_text_editor.locationEditText
import java.io.File
import java.io.FileOutputStream

class TextEditorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_editor)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        readFileContent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_text_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveFile) {
            val state = getCurrentStorageState()

            if (state) {
                writeTextToExternalStorage()
            } else {
                writeTextToInternalStorage()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun readFileContent() {
        val storageType = intent.extras?.getString(STORAGE_TYPE_EXTRAS) ?: getString(R.string.internal)
        val fileName = getFileName()

        when(storageType) {
            getString(R.string.internal) -> readFileContentFromInternalStorage(fileName)
            getString(R.string.external) -> readFileContentFromExternalStorage(fileName)
        }
    }

    private fun getCurrentStorageState(): Boolean {
        return getSharedPreferences(
            getString(R.string.application_preference), MODE_PRIVATE
        ).getBoolean(
            getString(R.string.storage_option), false
        )
    }

    private fun getFileName(): String {
        val fileName = intent.extras?.getString(FILE_NAME_EXTRAS) ?: ""
        editTextInputLayout.hint = fileName
        return fileName
    }

    private fun readFileContentFromInternalStorage(fileName: String) {
        var text = ""
        openFileInput(fileName).bufferedReader().useLines { lines ->
            text = lines.joinToString {
                it
            }
        }
        locationEditText.setText(text)
    }

    private fun readFileContentFromExternalStorage(fileName: String) {
        var text = ""
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(applicationContext, null)
        val primaryExternalStorage = externalStorageVolumes[0]

        val filePath = primaryExternalStorage.absolutePath + "/" + fileName
        Log.d("TAG", "filepath: $filePath")
        val file = File(filePath)

        file.inputStream().bufferedReader().useLines { lines ->
            text = lines.joinToString {
                it
            }
        }
        locationEditText.setText(text)
    }

    private fun writeTextToInternalStorage() {
        val textToSave = locationEditText.text.toString()
        val file = locationEditText.hint.toString()
        openFileOutput(file, MODE_PRIVATE).use {
            it.write(textToSave.toByteArray())
        }
    }

    private fun writeTextToExternalStorage() {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(applicationContext, null)
        val primaryExternalStorage = externalStorageVolumes[0]

        val filePath = primaryExternalStorage.absolutePath + "/" + locationEditText.hint.toString()
        val file = File(filePath)
        val textToSave = locationEditText.text.toString()
        FileOutputStream(file).use {
            it.write(textToSave.toByteArray())
        }
    }
}
