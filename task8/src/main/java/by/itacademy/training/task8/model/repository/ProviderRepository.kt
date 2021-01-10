package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.model.db.ContactsDao

class ProviderRepository(private val contactsDao: ContactsDao) {

    fun getContacts() = contactsDao.getAllContactsCursor()
}
