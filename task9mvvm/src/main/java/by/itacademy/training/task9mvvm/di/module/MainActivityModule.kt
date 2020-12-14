package by.itacademy.training.task9mvvm.di.module

import android.app.Application
import by.itacademy.training.task9mvvm.util.SupportSharedPreference
import by.itacademy.training.task9mvvm.util.SupportSharedPreferenceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainActivityModule {

    @Singleton
    @Provides
    fun provideSupportSharedPreference(application: Application): SupportSharedPreference {
        return SupportSharedPreferenceImpl(application)
    }
}
