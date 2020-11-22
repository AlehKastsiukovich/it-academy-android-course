package by.training.task7.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import by.training.task7.entity.Contact
import by.training.task7.model.ContactsDatabase
import by.training.task7.model.repository.ContactsRepository
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
