package by.itacademy.training.task9mvvm.di.component

import by.itacademy.training.task9mvvm.di.module.AppContextModule
import by.itacademy.training.task9mvvm.di.module.NetworkModule
import by.itacademy.training.task9mvvm.ui.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppContextModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)
}
