package by.itacademy.training.task9mvp.di.module

import by.itacademy.training.task9mvp.ui.view.MainActivity
import by.itacademy.training.task9mvp.ui.view.MainActivityView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainActivityView(mainActivity: MainActivity): MainActivityView = mainActivity

//    @Provides
//    fun provideMainViewModel(activity: AppCompatActivity): MainViewModel =
//        ViewModelProvider(activity).get(MainViewModel::class.java)
//
//    @Provides
//    fun provideCitiesViewModel(activity: AppCompatActivity): CitiesViewModel =
//        ViewModelProvider(activity).get(CitiesViewModel::class.java)
}
