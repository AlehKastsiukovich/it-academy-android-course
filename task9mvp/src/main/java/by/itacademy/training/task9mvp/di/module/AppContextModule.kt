package by.itacademy.training.task9mvp.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppContextModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideContext(): Application = application
}
