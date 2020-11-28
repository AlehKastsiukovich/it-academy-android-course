package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao
import by.itacademy.training.task8.util.CustomThreadPoolExecutor
import java.util.concurrent.CompletableFuture

class CompletableFutureMultiThreadingRepository(
    private val contactsDao: ContactsDao
) : BaseRepository(contactsDao) {

    override fun getContacts(): List<Contact> {
        var contactList = listOf<Contact>()
        val task = { contactsDao.getAllContacts() }
        val future = CompletableFuture.supplyAsync(task, CustomThreadPoolExecutor())
        return future.get()
    }

    override fun insert(contact: Contact) {
        CompletableFuture.runAsync { contactsDao.insertContact(contact) }
    }

    override fun update(contact: Contact) {
        CompletableFuture.runAsync { contactsDao.updateContact(contact) }
    }

    override fun delete(contact: Contact) {
        CompletableFuture.runAsync { contactsDao.deleteContact(contact) }
    }
}
