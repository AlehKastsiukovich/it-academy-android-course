package by.itacademy.training.task8.ui.view

import android.app.Application
import by.itacademy.training.task8.util.preference.SupportSharedPreference
import by.itacademy.training.task8.util.preference.SupportSharedPreferenceImpl

class App : Application() {

    val sharedPreferences: SupportSharedPreference = SupportSharedPreferenceImpl(this)
}
