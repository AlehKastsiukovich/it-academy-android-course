package by.training.task6.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_text_editor.locationEditText
import java.io.File
import java.io.FileOutputStream

const val REQUEST_WRITE_PERMISSION_ID = 9999

class FileManager(private val context: Context) {

    fun writeTextToInternalStorage() {
        if (isExternalStorageWritable()) {
            val textToSave = (context as Activity).locationEditText.text.toString()
            val file = (context as Activity).locationEditText.hint.toString()
            context.openFileOutput(file, AppCompatActivity.MODE_PRIVATE).use {
                it.write(textToSave.toByteArray())
            }
        } else {
            askPermission()
        }
    }

    fun writeTextToExternalStorage() {
        if (isExternalStorageWritable()) {
            val primaryExternalStorage = getPrimaryExternalStorageVolume()
            val filePath =
                primaryExternalStorage.absolutePath + "/" + (context as Activity).locationEditText.hint.toString()
            val file = File(filePath)
            val textToSave = (context as Activity).locationEditText.text.toString()
            FileOutputStream(file).use {
                it.write(textToSave.toByteArray())
            }
        } else {
            askPermission()
        }
    }

    fun readFileContentFromInternalStorage(fileName: String) {
        if (isExternalStorageWritable()) {
            var text = ""
            context.openFileInput(fileName).bufferedReader().useLines { lines ->
                text = lines.joinToString {
                    it
                }
            }
            (context as Activity).locationEditText.setText(text)
        } else {
            askPermission()
        }
    }

    fun readFileContentFromExternalStorage(fileName: String) {
        if (isExternalStorageWritable()) {
            var text = ""
            val primaryExternalStorage = getPrimaryExternalStorageVolume()

            val filePath = primaryExternalStorage.absolutePath + "/" + fileName
            val file = File(filePath)

            file.inputStream().bufferedReader().useLines { lines ->
                text = lines.joinToString {
                    it
                }
            }
            (context as Activity).locationEditText.setText(text)
        } else {
            askPermission()
        }
    }

    private fun getPrimaryExternalStorageVolume(): File {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(context.applicationContext, null)
        return externalStorageVolumes[0]
    }

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun askPermission() {
        val permission =
            ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                (context as Activity).requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_WRITE_PERMISSION_ID
                )
            }
        }
    }
}
