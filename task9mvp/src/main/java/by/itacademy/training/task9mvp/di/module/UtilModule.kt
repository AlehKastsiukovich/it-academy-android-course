package by.itacademy.training.task9mvp.di.module

import by.itacademy.training.task9mvp.ui.presenter.CurrentTemperatureUnitListener
import by.itacademy.training.task9mvp.ui.presenter.CurrentTemperatureUnitListenerImpl
import by.itacademy.training.task9mvp.util.SupportSharedPreference
import by.itacademy.training.task9mvp.util.SupportSharedPreferenceImpl
import dagger.Binds
import dagger.Module

@Module
interface UtilModule {

    @Binds
    fun bindCurrentTemperatureUnitListener(listener: CurrentTemperatureUnitListenerImpl):
            CurrentTemperatureUnitListener

    @Binds
    fun bindSupportSharePreference(support: SupportSharedPreferenceImpl):
        SupportSharedPreference
}
