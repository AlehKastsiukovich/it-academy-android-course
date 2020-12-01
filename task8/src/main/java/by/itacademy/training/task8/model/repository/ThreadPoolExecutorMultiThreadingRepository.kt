package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao
import by.itacademy.training.task8.util.CustomThreadPoolExecutor

class ThreadPoolExecutorMultiThreadingRepository(
    private val contactsDao: ContactsDao
) : BaseRepository {

    private val threadPoolExecutor = CustomThreadPoolExecutor()

    override fun getContacts(contactListener: ContactListener) {
        val task = { contactsDao.getAllContacts() }
        val result = threadPoolExecutor.submit(task)
    }

    override fun insert(contact: Contact) {
        val task = { contactsDao.insertContact(contact) }
        threadPoolExecutor.submit(task)
    }

    override fun update(contact: Contact) {
        val task = { contactsDao.updateContact(contact) }
        threadPoolExecutor.submit(task)
    }

    override fun delete(contact: Contact) {
        val task = { contactsDao.deleteContact(contact) }
        threadPoolExecutor.submit(task)
    }
}
