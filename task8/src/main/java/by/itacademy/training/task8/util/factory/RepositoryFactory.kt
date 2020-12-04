package by.itacademy.training.task8.util.factory

import by.itacademy.training.task8.model.db.ContactsDatabase
import by.itacademy.training.task8.model.repository.CompletableFutureMultiThreadingRepository
import by.itacademy.training.task8.model.repository.HandlerThreadMultiThreadingRepository
import by.itacademy.training.task8.model.repository.RxMultiThreadingRepository
import by.itacademy.training.task8.ui.view.App
import by.itacademy.training.task8.util.MultithreadingTypes

class RepositoryFactory(private val app: App) {

    private val dao = ContactsDatabase.getContactsDatabase(app).contactsDao()

    fun getRepository() =
        when (app.sharedPreferences.getCurrentMultithreadingType()) {
            MultithreadingTypes.RX -> RxMultiThreadingRepository(dao)
            MultithreadingTypes.CALLABLE_FUTURE -> CompletableFutureMultiThreadingRepository(dao)
            else -> HandlerThreadMultiThreadingRepository(dao)
        }
}
