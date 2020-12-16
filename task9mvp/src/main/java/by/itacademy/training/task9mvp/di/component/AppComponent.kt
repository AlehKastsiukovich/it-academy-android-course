package by.itacademy.training.task9mvp.di.component

import by.itacademy.training.task9mvp.di.module.AppContextModule
import by.itacademy.training.task9mvp.di.module.CompositeDisposableModule
import by.itacademy.training.task9mvp.di.module.DatabaseModule
import by.itacademy.training.task9mvp.di.module.NetworkModule
import by.itacademy.training.task9mvp.di.module.RepositoryModule
import by.itacademy.training.task9mvp.di.module.SharePreferenceModule
import by.itacademy.training.task9mvp.di.module.UtilModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppContextModule::class, NetworkModule::class,
        RepositoryModule::class, UtilModule::class,
        SharePreferenceModule::class, DatabaseModule::class,
        CompositeDisposableModule::class
    ]
)
@Singleton
interface AppComponent {

    fun activityComponentBuilder(): ActivityComponent.Builder
    fun citiesActivityComponentBuilder(): CitiesActivityComponent.Builder
}
