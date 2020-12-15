package by.itacademy.training.task9mvp.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import by.itacademy.training.task9mvp.ui.viewmodel.CitiesViewModel
import by.itacademy.training.task9mvp.ui.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainViewModel(activity: AppCompatActivity): MainViewModel =
        ViewModelProvider(activity).get(MainViewModel::class.java)

    @Provides
    fun provideCitiesViewModel(activity: AppCompatActivity): CitiesViewModel =
        ViewModelProvider(activity).get(CitiesViewModel::class.java)
}
