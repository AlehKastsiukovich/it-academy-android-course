package by.itacademy.training.task9mvvm.di.module

import android.content.Context
import by.itacademy.training.task9mvvm.util.SupportSharedPreferenceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SharePreferenceModule {

    @Provides
    fun provideSupportSharedPreferenceImpl(@Named("appContext") context: Context) =
        SupportSharedPreferenceImpl(context)
}
