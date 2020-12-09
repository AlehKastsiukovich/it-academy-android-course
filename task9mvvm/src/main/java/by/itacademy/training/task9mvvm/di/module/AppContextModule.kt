package by.itacademy.training.task9mvvm.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppContextModule(private val context: Context) {

    @Provides
    fun provideContext(context: Context): Context = context.applicationContext
}
