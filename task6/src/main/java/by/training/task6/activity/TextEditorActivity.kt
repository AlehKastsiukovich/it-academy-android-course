package by.training.task6.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.training.task6.R
import by.training.task6.util.FileManager
import kotlinx.android.synthetic.main.activity_text_editor.editTextInputLayout

class TextEditorActivity : AppCompatActivity() {

    private lateinit var fileManager: FileManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_editor)

        fileManager = FileManager(this)

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
                fileManager.writeTextToExternalStorage()
            } else {
                fileManager.writeTextToInternalStorage()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun readFileContent() {
        val storageType = intent.extras?.getString(STORAGE_TYPE_EXTRAS) ?: getString(R.string.internal)
        val fileName = getFileName()

        when (storageType) {
            getString(R.string.internal) -> fileManager.readFileContentFromInternalStorage(fileName)
            getString(R.string.external) -> fileManager.readFileContentFromExternalStorage(fileName)
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
}
