package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository(private val contactsDao: ContactsDao) {

    suspend fun getContacts(): List<Contact> {
        return withContext(Dispatchers.IO) {
            contactsDao.getAllContacts()
        }
    }

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
