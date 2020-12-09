package by.itacademy.training.task9mvvm.di.module

import by.itacademy.training.task9mvvm.util.DTOMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DtoModule {

    @Singleton
    @Provides
    fun provideDTOMapper(): DTOMapper = DTOMapper()
}
