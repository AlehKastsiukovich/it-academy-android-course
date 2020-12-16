package by.itacademy.training.task9mvp.di.module

import by.itacademy.training.task9mvp.ui.presenter.MainActivityPresenter
import by.itacademy.training.task9mvp.ui.presenter.MainActivityPresenterImpl
import dagger.Binds
import dagger.Module

@Module
interface PresenterModule {

    @Binds
    fun provideMainActivityPresenter(presenter: MainActivityPresenterImpl): MainActivityPresenter
}
