package by.itacademy.training.task8.util.factory

import by.itacademy.training.task8.model.db.ContactsDatabase
import by.itacademy.training.task8.model.repository.BaseRepository
import by.itacademy.training.task8.model.repository.CompletableFutureMultiThreadingRepository
import by.itacademy.training.task8.model.repository.HandlerThreadMultiThreadingRepository
import by.itacademy.training.task8.model.repository.RxMultiThreadingRepository
import by.itacademy.training.task8.ui.view.App
import by.itacademy.training.task8.util.MultithreadingType

class RepositoryFactory(
    private val app: App,
    private val onRepositoryTypeChangeListener: OnRepositoryTypeChangeListener
) {

    private val dao = ContactsDatabase.getContactsDatabase(app).contactsDao()

    fun getRepository() =
        when (app.sharedPreferences.getCurrentMultithreadingType()) {
            MultithreadingType.RX ->
                onRepositoryTypeChangeListener
                    .onRepositoryChange(RxMultiThreadingRepository(dao))
            MultithreadingType.CALLABLE_FUTURE ->
                onRepositoryTypeChangeListener
                    .onRepositoryChange(CompletableFutureMultiThreadingRepository(dao))
            else ->
                onRepositoryTypeChangeListener
                    .onRepositoryChange(HandlerThreadMultiThreadingRepository(dao))
        }
}

interface OnRepositoryTypeChangeListener {

    fun onRepositoryChange(type: BaseRepository)
}
