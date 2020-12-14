package by.itacademy.training.task9mvvm.di.component

import by.itacademy.training.task9mvvm.di.module.AppContextModule
import by.itacademy.training.task9mvvm.di.module.DatabaseModule
import by.itacademy.training.task9mvvm.di.module.MainActivityContextModule
import by.itacademy.training.task9mvvm.di.module.NetworkModule
import by.itacademy.training.task9mvvm.di.module.RepositoryModule
import by.itacademy.training.task9mvvm.di.module.SharePreferenceModule
import by.itacademy.training.task9mvvm.di.module.UtilModule
import by.itacademy.training.task9mvvm.ui.view.MainActivity
import by.itacademy.training.task9mvvm.ui.viewmodel.CitiesViewModel
import by.itacademy.training.task9mvvm.ui.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppContextModule::class, NetworkModule::class,
        RepositoryModule::class, UtilModule::class,
        SharePreferenceModule::class, MainActivityContextModule::class,
        DatabaseModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)
    fun inject(mainActivity: MainActivity)
    fun inject(citiesViewModel: CitiesViewModel)
}
