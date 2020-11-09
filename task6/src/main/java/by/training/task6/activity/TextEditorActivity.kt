package by.training.task6.activity

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import by.training.task6.R
import by.training.task6.adapter.StorageType
import by.training.task6.databinding.ActivityTextEditorBinding
import by.training.task6.util.FileManager
import by.training.task6.util.REQUEST_WRITE_PERMISSION_ID
import by.training.task6.util.StorageUtil
import kotlinx.android.synthetic.main.activity_text_editor.editTextInputLayout
import java.io.File

class TextEditorActivity : AppCompatActivity() {

    private lateinit var fileManager: FileManager
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityTextEditorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(getString(R.string.application_preference), MODE_PRIVATE)
        fileManager = FileManager()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        readFileContent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_text_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveFile) {
            writeToFile()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun readFileContent() {
        val storageType = intent.extras?.getSerializable(STORAGE_TYPE_EXTRAS) as StorageType
        val fileName = getFileName()

        if (storageType == StorageType.INTERNAL) {
            val file = File(filesDir, fileName)
            binding.locationEditText.setText(fileManager.readFileContentFromInternalStorage(file))
        } else {
            val primaryExternalStorage = getPrimaryExternalStorageVolume()
            val filePath = primaryExternalStorage.absolutePath + "/" + fileName
            val file = File(filePath)
            binding.locationEditText.setText(fileManager.readFileContentFromExternalStorage(file))
        }
    }

    private fun getFileName(): String {
        val fileName = intent.extras?.getString(FILE_NAME_EXTRAS) ?: ""
        editTextInputLayout.hint = fileName
        return fileName
    }

    private fun writeToFile() {
        if (StorageUtil.isExternal(sharedPreferences)) {
            writeToExternal()
        } else {
            writeToInternal()
        }
    }

    private fun askPermission() {
        val permission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_WRITE_PERMISSION_ID
                )
            }
        }
    }

    private fun writeToInternal() {
        val textToSave = binding.locationEditText.text.toString()
        val fileName = binding.locationEditText.hint.toString()
        val file = File(filesDir, fileName)
        fileManager.writeTextToInternalStorage(textToSave, file)
    }

    private fun writeToExternal() {
        if (isExternalStorageWritable()) {
            val primaryExternalStorage = getPrimaryExternalStorageVolume()
            val filePath =
                primaryExternalStorage.absolutePath + "/" + binding.locationEditText.hint.toString()
            val file = File(filePath)
            val textToSave = binding.locationEditText.text.toString()
            fileManager.writeTextToExternalStorage(textToSave, file)
        } else {
            askPermission()
        }
    }

    private fun getPrimaryExternalStorageVolume(): File {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(applicationContext, null)
        return externalStorageVolumes[0]
    }

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }
}
