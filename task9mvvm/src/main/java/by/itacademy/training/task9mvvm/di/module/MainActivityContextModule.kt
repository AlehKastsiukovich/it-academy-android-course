package by.itacademy.training.task9mvvm.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MainActivityContextModule(private val context: Context) {

    @Provides
    @Named("mainActivityContext")
    fun provideMainActivityContext(context: Context): Context = context
}
