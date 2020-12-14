package by.itacademy.training.task9mvvm.di.component

import androidx.appcompat.app.AppCompatActivity
import by.itacademy.training.task9mvvm.di.module.MainActivityModule
import by.itacademy.training.task9mvvm.ui.view.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModule::class])
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(activity: AppCompatActivity): Builder
        fun build(): ActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}
