package by.itacademy.training.task8.model.repository

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import by.itacademy.training.task8.model.db.ContactsDao
import by.itacademy.training.task8.model.entity.Contact
import by.itacademy.training.task8.util.ContactListener
import by.itacademy.training.task8.util.Event
import by.itacademy.training.task8.util.Status

class ThreadPoolExecutorMultiThreadingRepository(
    private val contactsDao: ContactsDao
) : BaseRepository {

    private val customThreadHandler = CustomThreadHandler().apply {
        start()
        initHandler()
    }

    private val handler = Handler(Looper.getMainLooper())

    override fun getContacts(contactListener: ContactListener) {
        customThreadHandler.executeTask {
            try {
                val contactList = contactsDao.getAllContacts()
                handler.post {
                    contactListener.onContactsGetListener(
                        Event(Status.SUCCESS, contactList, null)
                    )
                }
            } catch (exception: Exception) {
                handler.post {
                    contactListener.onContactsGetListener(
                        Event(Status.ERROR, null, exception.message)
                    )
                }
            }
        }
    }

    override fun insert(contact: Contact, contactListener: ContactListener) {
        customThreadHandler.executeTask {
            try {
                contactsDao.insertContact(contact)
            } catch (exception: Exception) {
                handler.post {
                    contactListener.onContactsAddError(
                        Event(Status.ERROR, null, exception.message)
                    )
                }
            }
        }
    }

    override fun update(contact: Contact, contactListener: ContactListener) {
        customThreadHandler.executeTask {
            try {
                contactsDao.updateContact(contact)
            } catch (exception: Exception) {
                handler.post {
                    contactListener.onContactsAddError(
                        Event(Status.ERROR, null, exception.message)
                    )
                }
            }
        }
    }

    override fun delete(contact: Contact, contactListener: ContactListener) {
        customThreadHandler.executeTask {
            try {
                contactsDao.deleteContact(contact)
            } catch (exception: Exception) {
                handler.post {
                    contactListener.onContactsAddError(
                        Event(Status.ERROR, null, exception.message)
                    )
                }
            }
        }
    }
}

class CustomThreadHandler : HandlerThread(CustomThreadHandler::class.java.name) {

    private lateinit var handler: Handler

    fun executeTask(task: () -> Unit) {
        handler.post(task)
    }

    fun initHandler() {
        handler = Handler(looper)
    }
}
