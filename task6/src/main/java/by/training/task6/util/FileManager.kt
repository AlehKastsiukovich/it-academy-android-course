package by.training.task6.util

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_text_editor.locationEditText
import java.io.File
import java.io.FileOutputStream

class FileManager(private val context: Context) {

    fun writeTextToInternalStorage() {
        val textToSave = (context as Activity).locationEditText.text.toString()
        val file = (context as Activity).locationEditText.hint.toString()
        context.openFileOutput(file, AppCompatActivity.MODE_PRIVATE).use {
            it.write(textToSave.toByteArray())
        }
    }

    fun writeTextToExternalStorage() {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(context.applicationContext, null)
        val primaryExternalStorage = externalStorageVolumes[0]

        val filePath =
            primaryExternalStorage.absolutePath + "/" + (context as Activity).locationEditText.hint.toString()
        val file = File(filePath)
        val textToSave = (context as Activity).locationEditText.text.toString()
        FileOutputStream(file).use {
            it.write(textToSave.toByteArray())
        }
    }

    fun readFileContentFromInternalStorage(fileName: String) {
        var text = ""
        context.openFileInput(fileName).bufferedReader().useLines { lines ->
            text = lines.joinToString {
                it
            }
        }
        (context as Activity).locationEditText.setText(text)
    }

    fun readFileContentFromExternalStorage(fileName: String) {
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
    }

    private fun getPrimaryExternalStorageVolume(): File {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(context.applicationContext, null)
        return externalStorageVolumes[0]
    }
}
