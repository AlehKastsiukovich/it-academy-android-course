package by.itacademy.training.task9mvvm.di.module

import android.app.Application
import by.itacademy.training.task9mvvm.util.SupportSharedPreferenceImpl
import dagger.Module
import dagger.Provides

@Module
class SharePreferenceModule {

    @Provides
    fun provideSupportSharedPreferenceImpl(application: Application) =
        SupportSharedPreferenceImpl(application)
}
