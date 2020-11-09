package by.training.task6.util

import android.content.SharedPreferences
import by.training.task6.adapter.StorageType

object StorageUtil {

    private const val STORAGE_TYPE = "Storage type"

    fun saveStorageType(storageType: StorageType, sharedPreferences: SharedPreferences) {
        sharedPreferences.edit().putString(STORAGE_TYPE, storageType.name).apply()
    }

    fun getStorageType(sharedPreferences: SharedPreferences): StorageType? {
        val preference = sharedPreferences.getString(STORAGE_TYPE, StorageType.INTERNAL.name)
        return preference?.let {
            StorageType.valueOf(it)
        }
    }

    fun isExternal(sharedPreferences: SharedPreferences) = getStorageType(sharedPreferences) == StorageType.EXTERNAL
}
