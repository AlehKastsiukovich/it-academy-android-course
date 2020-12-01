package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.model.ContactsDao

class RxMultiThreadingRepository(private val contactsDao: ContactsDao) : BaseRepository {

    override fun getContacts(contactListener: ContactListener) {
        
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
