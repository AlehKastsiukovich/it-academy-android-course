package by.training.task7.model.repository

import androidx.lifecycle.LiveData
import by.training.task7.entity.Contact
import by.training.task7.model.ContactsDao

class ContactsRepository(private val contactsDao: ContactsDao) {

    fun getContacts(): LiveData<List<Contact>> = contactsDao.getAllContacts()

    suspend fun insert(contact: Contact) {
        contactsDao.insertContact(contact)
    }

    suspend fun update(contact: Contact) {
        contactsDao.updateContact(contact)
    }

    suspend fun delete(contact: Contact) {
        contactsDao.deleteContact(contact)
    }
}
