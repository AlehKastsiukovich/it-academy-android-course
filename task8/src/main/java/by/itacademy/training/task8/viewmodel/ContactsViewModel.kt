package by.itacademy.training.task8.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao
import by.itacademy.training.task8.model.ContactsDatabase
import by.itacademy.training.task8.model.repository.BaseRepository
import by.itacademy.training.task8.model.repository.CompletableFutureMultiThreadingRepository

class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BaseRepository
    private val dao: ContactsDao = ContactsDatabase.getContactsDatabase(application).contactsDao()
    val contacts: List<Contact>
        get() = _contacts
    private var _contacts = mutableListOf<Contact>()

    init {
        repository = CompletableFutureMultiThreadingRepository(dao)
        getContactList()
    }

    fun getContactList(): List<Contact> {
        val contactList =  repository.getContacts() as MutableList<Contact>
        _contacts = contactList
        return _contacts
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
}
