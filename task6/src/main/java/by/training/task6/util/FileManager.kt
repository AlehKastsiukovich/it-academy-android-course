package by.training.task6.util

import java.io.File
import java.io.FileOutputStream

const val REQUEST_WRITE_PERMISSION_ID = 9999

class FileManager {

    fun writeTextToInternalStorage(text: String, file: File) {
        FileOutputStream(file).use {
            it.write(text.toByteArray())
        }
    }

    fun writeTextToExternalStorage(text: String, file: File) {
        FileOutputStream(file).use {
            it.write(text.toByteArray())
        }
    }

    fun readFileContentFromInternalStorage(file: File): String {
        var text = ""
        file.inputStream().bufferedReader().useLines { lines ->
            text = lines.joinToString {
                it
            }
        }
        return text
    }

    fun readFileContentFromExternalStorage(file: File): String {
        var text = ""
        file.inputStream().bufferedReader().useLines { lines ->
            text = lines.joinToString {
                it
            }
        }
        return text
    }
}
