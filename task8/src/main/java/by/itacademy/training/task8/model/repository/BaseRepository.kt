package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact

interface BaseRepository {

    fun getContacts(contactListener: ContactListener)

    fun insert(contact: Contact, contactListener: ContactListener)

    fun update(contact: Contact, contactListener: ContactListener)

    fun delete(contact: Contact, contactListener: ContactListener)
}
