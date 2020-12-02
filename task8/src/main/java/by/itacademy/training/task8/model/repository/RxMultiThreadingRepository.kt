package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.model.db.ContactsDao
import by.itacademy.training.task8.model.entity.Contact
import by.itacademy.training.task8.util.ContactListener
import by.itacademy.training.task8.util.Event
import by.itacademy.training.task8.util.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxMultiThreadingRepository(private val contactsDao: ContactsDao) : BaseRepository {

    override fun getContacts(contactListener: ContactListener) {
        val disposable = contactsDao.getAllContactsRx()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { contactListener.onContactsGetListener(Event.success(it)) },
                { contactListener.onContactsGetListener(Event.error(null, it.message)) }
            )
    }

    override fun insert(contact: Contact, contactListener: ContactListener) {
        val disposable = contactsDao.insertContactRx(contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {},
                { contactListener.onContactsAddError(Event(Status.ERROR, null, it.message)) }
            )
    }

    override fun update(contact: Contact, contactListener: ContactListener) {
        val disposable = contactsDao.updateContactRx(contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {},
                { contactListener.onContactsUpdateError(Event(Status.ERROR, null, it.message)) }
            )
    }

    override fun delete(contact: Contact, contactListener: ContactListener) {
        val disposable = contactsDao.deleteContactRx(contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {},
                { contactListener.onContactsDeleteError(Event(Status.ERROR, null, it.message)) }
            )
    }
}
