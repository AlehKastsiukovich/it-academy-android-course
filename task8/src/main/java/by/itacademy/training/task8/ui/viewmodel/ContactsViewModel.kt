package by.itacademy.training.task8.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.task8.model.db.ContactsDao
import by.itacademy.training.task8.model.entity.Contact
import by.itacademy.training.task8.model.repository.BaseRepository
import by.itacademy.training.task8.model.repository.RxMultiThreadingRepository
import by.itacademy.training.task8.ui.activity.ErrorInformer
import by.itacademy.training.task8.util.ContactListener
import by.itacademy.training.task8.util.Event
import by.itacademy.training.task8.util.Status

class ContactsViewModel(
    application: Application,
    dao: ContactsDao,
    private val informer: ErrorInformer

) : AndroidViewModel(application), ContactListener {

    private val repository: BaseRepository
    val contacts: LiveData<List<Contact>>
        get() = _contacts
    private var _contacts = MutableLiveData<List<Contact>>()

    init {
        _contacts.value = mutableListOf()
        repository = RxMultiThreadingRepository(dao)
        repository.getContacts(this)
    }

    override fun onContactsGetListener(event: Event<List<Contact>>) {
        when (event.status) {
            Status.SUCCESS -> { _contacts.value = event.data }
            Status.ERROR -> { informer.inform(event.message) }
            else -> Log.d("TAG", "Loading")
        }
    }

    fun add(contact: Contact) {
        repository.insert(contact, this)
    }

    fun update(contact: Contact) {
        repository.update(contact, this)
    }

    fun delete(contact: Contact) {
        repository.delete(contact, this)
    }

    override fun onContactsAddError(event: Event<Throwable>) {
        informer.inform(event.message)
    }

    override fun onContactsDeleteError(event: Event<Throwable>) {
        informer.inform(event.message)
    }

    override fun onContactsUpdateError(event: Event<Throwable>) {
        informer.inform(event.message)
    }
}
