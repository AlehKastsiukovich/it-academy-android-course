package by.itacademy.training.task9mvvm.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppContextModule(private val context: Context) {

    @Provides
    @Named("appContext")
    fun provideContext(context: Context): Context = context.applicationContext
}
