package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadPoolExecutorMultiThreadingRepository(
    private val contactsDao: ContactsDao
) : BaseRepository(contactsDao) {

    private val threadPoolExecutor = CustomThreadPoolExecutor()

    override fun getContacts(): List<Contact> {
        val task = { contactsDao.getAllContacts() }
        val result = threadPoolExecutor.submit(task)
        return result.get()
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

class CustomThreadPoolExecutor : ThreadPoolExecutor(
    CORE_POOL_SIZE,
    MAXIMUM_POOL_SIZE,
    KEEP_ALIVE_TIME,
    TimeUnit.SECONDS,
    SynchronousQueue()
) {
    companion object {
        private const val CORE_POOL_SIZE = 1
        private const val MAXIMUM_POOL_SIZE = 10
        private const val KEEP_ALIVE_TIME: Long = 20
        private const val QUEUE_CAPACITY = 10
    }
}
