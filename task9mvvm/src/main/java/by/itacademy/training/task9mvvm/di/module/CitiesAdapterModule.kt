package by.itacademy.training.task9mvvm.di.module

import by.itacademy.training.task9mvvm.ui.adapter.OnCityClickListener
import by.itacademy.training.task9mvvm.ui.view.CitiesActivity
import dagger.Module
import dagger.Provides

@Module
class CitiesAdapterModule {

    @Provides
    fun provideOnCityClickListener(activity: CitiesActivity): OnCityClickListener = activity
}
