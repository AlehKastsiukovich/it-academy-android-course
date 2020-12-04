package by.itacademy.training.task8.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class SupportSharedPreferenceImpl(private val application: Application) : SupportSharedPreference {

    override fun getSharedPreference(): SharedPreferences =
        application.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    override fun setCurrentMultithreadingType(type: MultithreadingTypes) =
        getSharedPreference()
            .edit()
            .putString(SHARED_PREFERENCES_TYPE_KEY, type.name)
            .apply()

    override fun getCurrentMultithreadingType(): MultithreadingTypes {
        val value = getSharedPreference()
            .getString(SHARED_PREFERENCES_KEY, MultithreadingTypes.RX.name)
        return MultithreadingTypes.valueOf(value ?: MultithreadingTypes.RX.name)
    }

    companion object {
        const val SHARED_PREFERENCES_KEY = "KEY"
        const val SHARED_PREFERENCES_TYPE_KEY = "TYPE"
    }
}
