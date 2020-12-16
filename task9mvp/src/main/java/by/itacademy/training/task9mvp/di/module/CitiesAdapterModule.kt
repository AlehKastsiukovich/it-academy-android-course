package by.itacademy.training.task9mvp.di.module

import by.itacademy.training.task9mvp.ui.adapter.OnCityClickListener
import by.itacademy.training.task9mvp.ui.view.CitiesActivity
import by.itacademy.training.task9mvp.ui.view.CityActivityView
import dagger.Module
import dagger.Provides

@Module
class CitiesAdapterModule {

    @Provides
    fun provideOnCityClickListener(activity: CitiesActivity): OnCityClickListener = activity

    @Provides
    fun provideCitiesActivityView(activity: CitiesActivity): CityActivityView = activity
}
