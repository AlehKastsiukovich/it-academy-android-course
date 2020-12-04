package by.itacademy.training.task8.util.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import by.itacademy.training.task8.util.MultithreadingType

class SupportSharedPreferenceImpl(private val application: Application) : SupportSharedPreference {

    override fun getSharedPreference(): SharedPreferences =
        application.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    override fun setCurrentMultithreadingType(type: MultithreadingType) =
        getSharedPreference()
            .edit()
            .putString(SHARED_PREFERENCES_TYPE_KEY, type.name)
            .apply()

    override fun getCurrentMultithreadingType(): MultithreadingType {
        val value = getSharedPreference()
            .getString(SHARED_PREFERENCES_TYPE_KEY, MultithreadingType.RX.name)
        return MultithreadingType.valueOf(value ?: MultithreadingType.RX.name)
    }

    companion object {
        const val SHARED_PREFERENCES_KEY = "KEY"
        const val SHARED_PREFERENCES_TYPE_KEY = "TYPE"
    }
}
