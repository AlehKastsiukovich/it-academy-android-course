package by.itacademy.training.task9mvp.di.module

import by.itacademy.training.task9mvp.ui.view.MainActivity
import by.itacademy.training.task9mvp.ui.view.MainActivityView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainActivityView(mainActivity: MainActivity): MainActivityView = mainActivity
}
