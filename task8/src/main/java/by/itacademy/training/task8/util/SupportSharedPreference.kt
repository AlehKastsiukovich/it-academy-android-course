package by.itacademy.training.task8.util

import android.content.SharedPreferences

interface SupportSharedPreference {

    fun getSharedPreference(): SharedPreferences

    fun setCurrentMultithreadingType(type: MultithreadingTypes)

    fun getCurrentMultithreadingType(): String?
}
