package by.itacademy.training.task8.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao
import by.itacademy.training.task8.model.ContactsDatabase
import by.itacademy.training.task8.model.repository.BaseRepository
import by.itacademy.training.task8.model.repository.ContactListener
import by.itacademy.training.task8.model.repository.RxMultiThreadingRepository
import by.itacademy.training.task8.util.Event
import by.itacademy.training.task8.util.Status

class ContactsViewModel(application: Application) : AndroidViewModel(application), ContactListener {

    private val repository: BaseRepository
    private val dao: ContactsDao = ContactsDatabase.getContactsDatabase(application).contactsDao()
    val contacts: LiveData<List<Contact>>
        get() = _contacts
    private var _contacts = MutableLiveData<List<Contact>>()

    init {
        repository = RxMultiThreadingRepository(dao)
        repository.getContacts(this)
    }

    fun add(contact: Contact) {
        repository.insert(contact)
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }

    fun edit(contact: Contact) {
        repository.update(contact)
    }

    override fun invoke(event: Event<List<Contact>>) {
        when (event.status) {
            Status.SUCCESS -> { _contacts.value = event.data }
            Status.ERROR -> { Log.d("TAG", "Error") }
        }
    }
}
