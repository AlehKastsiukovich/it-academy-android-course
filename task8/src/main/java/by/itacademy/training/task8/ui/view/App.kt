package by.itacademy.training.task8.ui.view

import android.app.Application
import by.itacademy.training.task8.util.SupportSharedPreference
import by.itacademy.training.task8.util.SupportSharedPreferenceImpl

class App : Application() {

    val sharedPreferences: SupportSharedPreference = SupportSharedPreferenceImpl(this)
}
