package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao

abstract class BaseRepository(private val contactsDao: ContactsDao) {

    abstract fun getContacts(): List<Contact>

    abstract fun insert(contact: Contact)

    abstract fun update(contact: Contact)

    abstract fun delete(contact: Contact)
}
