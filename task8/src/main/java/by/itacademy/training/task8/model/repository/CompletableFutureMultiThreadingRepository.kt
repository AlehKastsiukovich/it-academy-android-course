package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao
import by.itacademy.training.task8.util.CustomThreadPoolExecutor
import by.itacademy.training.task8.util.Event
import by.itacademy.training.task8.util.Status
import java.util.concurrent.CompletableFuture

class CompletableFutureMultiThreadingRepository(private val contactsDao: ContactsDao) :
    BaseRepository {

    override fun getContacts(contactListener: ContactListener) {
        val task = { contactsDao.getAllContacts() }
        val future = CompletableFuture.supplyAsync(task, CustomThreadPoolExecutor())
        future.apply {
            thenAccept { result -> contactListener.invoke(Event(Status.SUCCESS, result, null)) }
            val x = get()
        }
    }

    override fun insert(contact: Contact) {
        TODO("Not yet implemented")
    }

    override fun update(contact: Contact) {
        TODO("Not yet implemented")
    }

    override fun delete(contact: Contact) {
        TODO("Not yet implemented")
    }
}
