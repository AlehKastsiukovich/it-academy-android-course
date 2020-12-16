package by.itacademy.training.task9mvp.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class CompositeDisposableModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}
