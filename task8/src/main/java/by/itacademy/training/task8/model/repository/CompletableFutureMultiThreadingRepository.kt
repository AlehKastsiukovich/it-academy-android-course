package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao

class CompletableFutureMultiThreadingRepository(
    private val contactsDao: ContactsDao
) : BaseRepository(
    contactsDao
) {

    override fun getContacts(): List<Contact> {
        TODO("Not yet implemented")
    }

    override fun insert(contact: Contact) {
        TODO("Not yet implemented")
    }

    override fun update(contact: Contact) {
        TODO("Not yet implemented")
    }

    override fun delete(contact: Contact) {
        TODO("Not yet implemented")
    }
}
