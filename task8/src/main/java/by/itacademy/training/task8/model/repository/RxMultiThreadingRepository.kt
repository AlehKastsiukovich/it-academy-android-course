package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao
import by.itacademy.training.task8.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxMultiThreadingRepository(private val contactsDao: ContactsDao) : BaseRepository {

    override fun getContacts(contactListener: ContactListener) {
        val disposable = contactsDao.getAllContactsRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    contactListener.invoke(Event.success(it))
                },
                {
                    contactListener.invoke(Event.error(null, it.message))
                }
            )
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
