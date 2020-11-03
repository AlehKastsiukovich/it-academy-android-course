package by.training.task6.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import by.training.task6.R
import kotlinx.android.synthetic.main.activity_main.addFileButton
import kotlinx.android.synthetic.main.activity_main.filesListView
import kotlinx.android.synthetic.main.dialog_item.view.fileName
import java.io.File

const val FILE_NAME_EXTRAS = "Filename"

class MainActivity : AppCompatActivity() {

    private lateinit var fileAdapter: ArrayAdapter<String>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val files: Array<String> = fileList()
        fileAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files)
        filesListView.adapter = fileAdapter

        addFileButton.setOnClickListener {
            dialogInit()
        }

        filesListView.setOnItemClickListener { _, view, _, _ ->
            val text = (view as TextView).text.toString()
            startFileEditor(text)
        }

        sharedPreferences = getSharedPreferences(
            getString(R.string.application_preference), MODE_PRIVATE
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_instance_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.openSettingsMenuButton) {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dialogInit() {
        val dialogItemView = layoutInflater.inflate(R.layout.dialog_item, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogItemView)
            .setPositiveButton(getString(R.string.save)) { _, _ ->
                val fileName =
                    dialogItemView.fileName.text.toString() + getString(R.string.txt_format)
                createFile(fileName)
                startFileEditor(fileName)
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            .setTitle(R.string.enter_file_name)
            .create()

        dialog.show()
        prepareDialogView(dialog)
    }

    private fun prepareDialogView(dialog: AlertDialog) {
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        val negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        layoutParams.weight = 10F
        layoutParams.gravity = Gravity.CENTER

        positiveButton.layoutParams = layoutParams
        negativeButton.layoutParams = layoutParams
    }

    private fun startFileEditor(fileName: String) {
        val intent = Intent(this, TextEditorActivity::class.java)
        intent.putExtra(FILE_NAME_EXTRAS, fileName)
        startActivity(intent)
    }

    private fun createFile(fileName: String) {
        val state =
            sharedPreferences.getBoolean(getString(R.string.storage_option), false)

        if (state) {
            val externalStorageVolumes: Array<out File> =
                ContextCompat.getExternalFilesDirs(applicationContext, null)
            val primaryExternalStorage = externalStorageVolumes[0]
            Log.d("TAG", primaryExternalStorage.absolutePath)
            File(primaryExternalStorage, fileName).createNewFile()
        } else {
            File(filesDir, fileName).createNewFile()
        }
    }
}
