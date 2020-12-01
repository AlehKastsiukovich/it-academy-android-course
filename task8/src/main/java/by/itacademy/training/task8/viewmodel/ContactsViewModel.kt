package by.itacademy.training.task8.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao
import by.itacademy.training.task8.model.ContactsDatabase
import by.itacademy.training.task8.model.repository.BaseRepository
import by.itacademy.training.task8.model.repository.RxMultiThreadingRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BaseRepository
    private val dao: ContactsDao = ContactsDatabase.getContactsDatabase(application).contactsDao()
    val contacts: List<Contact>
        get() = _contacts
    private var _contacts = mutableListOf<Contact>()

    init {
        repository = RxMultiThreadingRepository(dao)
        Observable.fromArray(repository.getContacts())
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _contacts.addAll(it)
            }
    }

    fun getContactList(): List<Contact> {
        val contactList = repository.getContacts() as MutableList<Contact>
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
