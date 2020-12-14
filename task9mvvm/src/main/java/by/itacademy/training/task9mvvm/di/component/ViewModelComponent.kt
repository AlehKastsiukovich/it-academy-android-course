package by.itacademy.training.task9mvvm.di.component

import by.itacademy.training.task9mvvm.ui.viewmodel.MainViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelComponent
    }

    fun inject(mainViewModel: MainViewModel)
}
