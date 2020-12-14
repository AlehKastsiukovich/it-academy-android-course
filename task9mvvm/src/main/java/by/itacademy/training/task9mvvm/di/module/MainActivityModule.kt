package by.itacademy.training.task9mvvm.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import by.itacademy.training.task9mvvm.ui.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainViewModel(activity: AppCompatActivity): MainViewModel =
        ViewModelProvider(activity).get(MainViewModel::class.java)
}
