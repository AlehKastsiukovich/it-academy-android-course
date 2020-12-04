package by.itacademy.training.task8.util.preference

import android.content.SharedPreferences
import by.itacademy.training.task8.util.MultithreadingType

interface SupportSharedPreference {

    fun getSharedPreference(): SharedPreferences

    fun setCurrentMultithreadingType(type: MultithreadingType)

    fun getCurrentMultithreadingType(): MultithreadingType
}
