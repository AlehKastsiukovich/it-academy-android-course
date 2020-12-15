package by.itacademy.training.task9mvp.app

import android.app.Application
import by.itacademy.training.task9mvp.di.component.AppComponent
import by.itacademy.training.task9mvp.di.component.DaggerAppComponent
import by.itacademy.training.task9mvp.di.module.AppContextModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appContextModule(AppContextModule(this))
            .build()
    }
}
