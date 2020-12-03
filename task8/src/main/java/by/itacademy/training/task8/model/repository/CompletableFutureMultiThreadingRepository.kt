package by.itacademy.training.task8.model.repository

import android.os.Handler
import android.os.Looper
import by.itacademy.training.task8.model.db.ContactsDao
import by.itacademy.training.task8.model.entity.Contact
import by.itacademy.training.task8.util.ContactListener
import by.itacademy.training.task8.util.CustomThreadPoolExecutor
import by.itacademy.training.task8.util.Event
import by.itacademy.training.task8.util.Status
import java.util.concurrent.CompletableFuture

class CompletableFutureMultiThreadingRepository(
    private val contactsDao: ContactsDao
) : BaseRepository {

    private val handler = Handler(Looper.getMainLooper())
    private val threadPoolExecutor = CustomThreadPoolExecutor()

    override fun getContacts(contactListener: ContactListener) {
        val task = { contactsDao.getAllContacts() }
        CompletableFuture.supplyAsync(task, threadPoolExecutor)
            .thenAcceptAsync {
                handler.post {
                    contactListener.onContactsGetListener(
                        Event(
                            Status.SUCCESS,
                            it,
                            null
                        )
                    )
                }
            }
            .exceptionally {
                contactListener.onContactsGetListener(Event(Status.SUCCESS, null, null)) as Void
            }
    }

    override fun insert(contact: Contact, contactListener: ContactListener) {
        val task = { contactsDao.insertContact(contact) }
        CompletableFuture.supplyAsync(task, CustomThreadPoolExecutor())
            .exceptionally {
                contactListener.onContactsAddError(Event(Status.ERROR, null, it.message))
            }
    }

    override fun update(contact: Contact, contactListener: ContactListener) {
        val task = { contactsDao.updateContact(contact) }
        CompletableFuture.supplyAsync(task, threadPoolExecutor)
            .exceptionally {
                contactListener.onContactsUpdateError(Event(Status.ERROR, null, it.message))
            }
    }

    override fun delete(contact: Contact, contactListener: ContactListener) {
        val task = { contactsDao.deleteContact(contact) }
        CompletableFuture.supplyAsync(task, threadPoolExecutor)
            .exceptionally {
                contactListener.onContactsDeleteError(Event(Status.ERROR, null, it.message))
            }
    }
}
