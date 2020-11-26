package by.itacademy.training.task8.model.repository

import androidx.lifecycle.LiveData
import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao

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
