package by.itacademy.training.task9mvp.di.component

import by.itacademy.training.task9mvp.di.module.MainActivityModule
import by.itacademy.training.task9mvp.di.module.PresenterModule
import by.itacademy.training.task9mvp.ui.view.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModule::class, PresenterModule::class])
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(mainActivity: MainActivity): Builder
        fun build(): ActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}
