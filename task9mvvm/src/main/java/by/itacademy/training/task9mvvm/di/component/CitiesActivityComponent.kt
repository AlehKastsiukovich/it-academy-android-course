package by.itacademy.training.task9mvvm.di.component

import by.itacademy.training.task9mvvm.di.module.CitiesAdapterModule
import by.itacademy.training.task9mvvm.ui.view.CitiesActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [CitiesAdapterModule::class])
interface CitiesActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(citiesActivity: CitiesActivity): Builder
        fun build(): CitiesActivityComponent
    }

    fun inject(citiesActivity: CitiesActivity)
}
