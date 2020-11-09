package by.training.task6.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import by.training.task6.R
import by.training.task6.adapter.StorageType
import by.training.task6.adapter.TextEditorAdapter
import by.training.task6.adapter.TextFile
import by.training.task6.databinding.ActivityMainBinding
import by.training.task6.databinding.DialogItemBinding
import by.training.task6.util.StorageUtil
import java.io.File

const val FILE_NAME_EXTRAS = "Filename"
const val STORAGE_TYPE_EXTRAS = "Storage type"

class MainActivity : AppCompatActivity(), TextEditorAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var textEditorAdapter: TextEditorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(getString(R.string.application_preference), MODE_PRIVATE)

        listenFileAddButton()
        initAdapter()
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

    override fun onItemClick(textFile: TextFile) {
        textFile.apply {
            startFileEditor(fileName, storageType)
        }
    }

    private fun listenFileAddButton() {
        binding.addFileButton.setOnClickListener {
            dialogInit()
        }
    }

    private fun initAdapter() {
        val internalStorageFiles = getInternalStorageFiles()
        val externalFiles = getExternalStorageFiles()

        textEditorAdapter = TextEditorAdapter(this).apply {
            addFiles(internalStorageFiles)
            addFiles(externalFiles)
        }
        binding.recyclerView.apply {
            adapter = textEditorAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun getInternalStorageFiles(): List<TextFile> {
        val list = mutableListOf<TextFile>()
        val internalStorageFiles = fileList()
        internalStorageFiles.forEach {
            list.add(TextFile(it, StorageType.INTERNAL))
        }
        return list
    }

    private fun getExternalStorageFiles(): List<TextFile> {
        val list = mutableListOf<TextFile>()
        val externalFiles = getPrimaryExternalStorageVolume().list()
        externalFiles?.forEach {
            list.add(TextFile(it, StorageType.EXTERNAL))
        }
        return list
    }

    private fun getPrimaryExternalStorageVolume(): File {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(applicationContext, null)
        return externalStorageVolumes[0]
    }

    private fun dialogInit() {
        val dialogItemBinding = DialogItemBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogItemBinding.root)
            .setPositiveButton(getString(R.string.save)) { _, _ ->
                val fileName =
                    dialogItemBinding.fileName.text.toString() + getString(R.string.txt_format)
                create(fileName, sharedPreferences, this)
                if (StorageUtil.isExternal(sharedPreferences)) {
                    startFileEditor(fileName, StorageType.EXTERNAL)
                } else {
                    startFileEditor(fileName, StorageType.INTERNAL)
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

    private fun startFileEditor(fileName: String, storageType: StorageType) {
        val intent = Intent(this, TextEditorActivity::class.java).apply {
            putExtra(FILE_NAME_EXTRAS, fileName)
            putExtra(STORAGE_TYPE_EXTRAS, storageType)
        }
        startActivity(intent)
    }

    companion object FileFactory {
        fun create(fileName: String, sharedPreferences: SharedPreferences, activity: MainActivity) {
            if (StorageUtil.isExternal(sharedPreferences)) {
                File(activity.getPrimaryExternalStorageVolume(), fileName).createNewFile()
            } else {
                File(activity.filesDir, fileName).createNewFile()
            }
        }
    }
}
