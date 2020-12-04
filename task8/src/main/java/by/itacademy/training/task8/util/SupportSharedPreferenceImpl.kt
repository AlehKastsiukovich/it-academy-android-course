package by.itacademy.training.task8.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SupportSharedPreferenceImpl(private val activity: Activity) : SupportSharedPreference {

    override fun getSharedPreference(): SharedPreferences =
        activity.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    override fun setCurrentMultithreadingType(string: String) {
    }

    override fun getCurrentMultithreadingType(): String? =
        getSharedPreference().getString(SHARED_PREFERENCES_KEY, "")

    companion object {
        const val SHARED_PREFERENCES_KEY = "KEY"
    }
}
