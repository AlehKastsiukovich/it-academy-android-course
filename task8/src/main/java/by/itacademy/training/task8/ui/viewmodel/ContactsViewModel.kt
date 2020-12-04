package by.itacademy.training.task8.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.itacademy.training.task8.model.entity.Contact
import by.itacademy.training.task8.model.repository.BaseRepository
import by.itacademy.training.task8.ui.view.App
import by.itacademy.training.task8.ui.view.ErrorInformer
import by.itacademy.training.task8.util.ContactListener
import by.itacademy.training.task8.util.Event
import by.itacademy.training.task8.util.Status
import by.itacademy.training.task8.util.factory.OnRepositoryTypeChangeListener
import by.itacademy.training.task8.util.factory.RepositoryFactory

class ContactsViewModel(
    application: Application,
    private val informer: ErrorInformer
) : AndroidViewModel(application), ContactListener, OnRepositoryTypeChangeListener {

    private lateinit var repository: BaseRepository
    val contacts: LiveData<Event<List<Contact>>>
        get() = _contacts
    private var _contacts = MutableLiveData<Event<List<Contact>>>()

    init {
        _contacts.value = Event(Status.SUCCESS, mutableListOf(), null)
        RepositoryFactory(application as App, this).initRepository()
        repository.getContacts(this)
    }

    override fun onRepositoryChange(repository: BaseRepository) {
        this.repository = repository
    }

    override fun onContactsGetListener(event: Event<List<Contact>>) {
        _contacts.value = event
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
