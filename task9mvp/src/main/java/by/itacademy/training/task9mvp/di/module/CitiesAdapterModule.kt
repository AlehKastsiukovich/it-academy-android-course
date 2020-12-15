package by.itacademy.training.task9mvp.di.module

import by.itacademy.training.task9mvp.ui.adapter.OnCityClickListener
import by.itacademy.training.task9mvp.ui.view.CitiesActivity
import dagger.Module
import dagger.Provides

@Module
class CitiesAdapterModule {

    @Provides
    fun provideOnCityClickListener(activity: CitiesActivity): OnCityClickListener = activity
}
