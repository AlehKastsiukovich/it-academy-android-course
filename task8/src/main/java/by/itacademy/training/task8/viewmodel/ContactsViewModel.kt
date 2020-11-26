package by.itacademy.training.task8.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDatabase
import by.itacademy.training.task8.model.repository.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    val contacts: LiveData<List<Contact>>
    private val repository: ContactsRepository

    init {
        val dao = ContactsDatabase.getContactsDatabase(application).contactsDao()
        repository = ContactsRepository(dao)
        contacts = repository.getContacts()
    }

    fun add(item: Contact) = viewModelScope.launch {
        repository.insert(item)
    }

    fun edit(item: Contact) = viewModelScope.launch {
        repository.update(item)
    }

    fun delete(item: Contact) = viewModelScope.launch {
        repository.delete(item)
    }
}
