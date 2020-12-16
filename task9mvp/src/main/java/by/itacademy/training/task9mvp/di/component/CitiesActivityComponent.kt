package by.itacademy.training.task9mvp.di.component

import by.itacademy.training.task9mvp.di.module.CitiesAdapterModule
import by.itacademy.training.task9mvp.di.module.PresenterModule
import by.itacademy.training.task9mvp.ui.view.CitiesActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [CitiesAdapterModule::class, PresenterModule::class])
interface CitiesActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(citiesActivity: CitiesActivity): Builder
        fun build(): CitiesActivityComponent
    }

    fun inject(citiesActivity: CitiesActivity)
}
