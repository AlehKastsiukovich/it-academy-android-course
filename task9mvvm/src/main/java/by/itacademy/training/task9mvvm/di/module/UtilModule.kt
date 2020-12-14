package by.itacademy.training.task9mvvm.di.module

import by.itacademy.training.task9mvvm.util.CurrentTemperatureUnitListener
import by.itacademy.training.task9mvvm.util.CurrentTemperatureUnitListenerImpl
import by.itacademy.training.task9mvvm.util.SupportSharedPreference
import by.itacademy.training.task9mvvm.util.SupportSharedPreferenceImpl
import dagger.Binds
import dagger.Module

@Module
interface UtilModule {

    @Binds
    fun bindCurrentTemperatureUnitListener(listener: CurrentTemperatureUnitListenerImpl):
        CurrentTemperatureUnitListener
//
//    @Binds
//    fun bindSupportSharePreference(support: SupportSharedPreferenceImpl):
//        SupportSharedPreference
}
