package by.training.task6.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_main.externalStorageFilesListView
import kotlinx.android.synthetic.main.activity_main.filesListView
import kotlinx.android.synthetic.main.dialog_item.view.fileName
import java.io.File

const val FILE_NAME_EXTRAS = "Filename"
const val STORAGE_TYPE_EXTRAS = "Storage type"

class MainActivity : AppCompatActivity() {

    private lateinit var internalFileAdapter: ArrayAdapter<String>
    private lateinit var externalFileAdapter: ArrayAdapter<String>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(
            getString(R.string.application_preference), MODE_PRIVATE
        )

        listenFileAddButton()
        initAdapters()
        addItemListenersToAdapters()
    }

    override fun onResume() {
        super.onResume()
        internalFileAdapter.notifyDataSetChanged()
        externalFileAdapter.notifyDataSetChanged()
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

    private fun listenFileAddButton() {
        addFileButton.setOnClickListener {
            dialogInit()
        }
    }

    private fun initAdapters() {
        val internalStorageFiles: Array<String> = fileList()
        internalFileAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            internalStorageFiles
        )
        filesListView.adapter = internalFileAdapter

        val externalFiles: Array<String> = getPrimaryExternalStorageVolume().list()
        externalFileAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            externalFiles
        )
        externalStorageFilesListView.adapter = externalFileAdapter
    }

    private fun getPrimaryExternalStorageVolume(): File {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(applicationContext, null)
        return externalStorageVolumes[0]
    }

    private fun addItemListenersToAdapters() {
        filesListView.setOnItemClickListener { _, view, _, _ ->
            val text = (view as TextView).text.toString()
            startFileEditor(text, getString(R.string.internal))
        }

        externalStorageFilesListView.setOnItemClickListener { _, view, _, _ ->
            val text = (view as TextView).text.toString()
            startFileEditor(text, getString(R.string.external))
        }
    }

    private fun dialogInit() {
        val dialogItemView = layoutInflater.inflate(R.layout.dialog_item, null)
        val storageState = getCurrentStorageState()

        val dialog = AlertDialog.Builder(this)
            .setView(dialogItemView)
            .setPositiveButton(getString(R.string.save)) { _, _ ->
                val fileName =
                    dialogItemView.fileName.text.toString() + getString(R.string.txt_format)
                createFile(fileName)
                if (storageState) {
                    startFileEditor(fileName, getString(R.string.external))
                } else {
                    startFileEditor(fileName, getString(R.string.internal))
                }
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
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

    private fun startFileEditor(fileName: String, storageType: String) {
        val intent = Intent(this, TextEditorActivity::class.java)
        intent.putExtra(FILE_NAME_EXTRAS, fileName)
        intent.putExtra(STORAGE_TYPE_EXTRAS, storageType)
        startActivity(intent)
    }

    private fun createFile(fileName: String) {
        val storageState =
            sharedPreferences.getBoolean(getString(R.string.storage_option), false)

        if (storageState) {
            val primaryExternalStorage = getPrimaryExternalStorageVolume()
            File(primaryExternalStorage, fileName).createNewFile()
        } else {
            File(filesDir, fileName).createNewFile()
        }
    }

    private fun getCurrentStorageState(): Boolean {
        return sharedPreferences.getBoolean(getString(R.string.storage_option), false)
    }
}
